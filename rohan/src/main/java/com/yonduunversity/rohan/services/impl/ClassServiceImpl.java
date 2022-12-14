package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.exception.*;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.dto.ClassCourseDTO;
import com.yonduunversity.rohan.models.dto.StudentDTO;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.CourseRepo;
import com.yonduunversity.rohan.repository.ProjectRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.repository.pagination.ClassRepoPaginate;
import com.yonduunversity.rohan.repository.pagination.StudentRepoPaginate;
import com.yonduunversity.rohan.services.ClassService;
import com.yonduunversity.rohan.services.EmailSenderService;
import com.yonduunversity.rohan.util.CertificateGen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final ClassBatchRepo classBatchRepo;
    private final ProjectRepo projectRepo;
    private final ClassRepoPaginate classRepoPaginate;
    private final StudentRepoPaginate studentRepoPaginate;
    private final EmailSenderService emailSenderService;

    @Override
    public ClassBatch saveClass(ClassBatch classBatch, String whoAdded)  {
        Course course = courseRepo.findCourseByCode(classBatch.getCourse().getCode());
        User userSme = userRepo.findByEmail(whoAdded);

        if(userSme.isActive() && course.isActive()){
            int totalPercentage = classBatch.getExercisePercentage() + classBatch.getQuizPercentage()
                    + classBatch.getAttendancePercentage() + classBatch.getProjectPercentage();
            if (totalPercentage == 100 && classBatch.getEndDate().compareTo(classBatch.getStartDate()) > 0) {
                classBatch.setCourse(course);
                classBatch.setSme(userSme);
                classBatch.setBatch(classBatchRepo.findClassBatchByCourseCode(course.getCode()) + 1);
                Project project = new Project();
                project.setClassBatch(classBatch);
                projectRepo.save(project);
                classBatch.setProject(project);
                classBatch.setActive(true);

                classBatchRepo.save(classBatch);
                course.getClassBatches().add(classBatch);
                courseRepo.save(course);
                return classBatch;
            } else {
                throw new CustomParameterConstraintException();
            }
        }else{
            throw new CustomDataNotFoundException();
        }

    }

    @Override
    public ClassBatch enrollStudent(String email, String code, long batchNumber) throws Exception {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
        Student studentEnrolled = studentRepo.findByEmail(email);
        if(studentEnrolled.isActive() && classBatch.isActive()){
            studentEnrolled.setActive(true);
            classBatch.getStudents().add(studentEnrolled);
            studentEnrolled.getClassBatches().add(classBatch);
            studentRepo.save(studentEnrolled);
            return classBatchRepo.save(classBatch);

        }else{
            throw new CustomDataNotFoundException();
        }
    }

    @Override
    public ClassBatch unEnrollStudent(String email, String code, long batchNumber) throws Exception {

        if (classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber) != null
                || studentRepo.findByEmail(email) != null) {
            ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
            Student studentUnEnroll = studentRepo.findByEmail(email);
            LocalDate localDate = LocalDate.now();
            boolean studentClassStatus = localDate.compareTo(classBatch.getStartDate()) > 0 && localDate.compareTo(classBatch.getEndDate()) < 0;
            if(studentClassStatus){
                throw new CustomParameterConstraintException("This student is currently enrolled in this class:" + classBatch.getCourse().getCode() + " - " + classBatch.getBatch());
            }else{
                classBatch.getStudents().remove(studentUnEnroll);
                studentUnEnroll.getClassBatches().remove(classBatch);
                studentRepo.save(studentUnEnroll);
                return classBatchRepo.save(classBatch);
            }

        }else{

            throw new CustomDataNotFoundException();
        }
    }

    @Override
    public ClassBatch deactivateClass(String code, long batchNumber) throws Exception {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
        LocalDate localDate = LocalDate.now();
        if(localDate.compareTo(classBatch.getStartDate()) > 0 && localDate.compareTo(classBatch.getEndDate()) < 0) {
            throw new CustomParameterConstraintException("This Class is still on-going, thus it cannot be terminated.");
        }else {
            classBatch.getStudents().removeAll(classBatch.getStudents());
            classBatch.setActive(false);
            return classBatchRepo.save(classBatch);
        }
    }

    @Override
    public List<ClassBatch> findStudentClass(String email) {
        Student studentUnEnroll = studentRepo.findByEmail(email);
        return studentUnEnroll.getClassBatches().stream().toList();
    }

    @Override
    public List<ClassBatch> getAllClassBatch() {
        return classBatchRepo.findAll();
    }

    @Override
    public List<StudentDTO> getClassStudents(String code, long batchNumber, int pageNumber, int pageSize) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        List<StudentDTO> pagedResult = studentRepoPaginate.findStudentByClassBatches(classBatch, paging).stream().map(StudentDTO::new).toList();

        return pagedResult;
    }

    @Override
    public List<ClassBatch> getAllClassBatch(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ClassBatch> pagedResult = classRepoPaginate.findAll(paging);
        return pagedResult.stream().toList();
    }

    @Override
    public List<ClassCourseDTO> getClassByKeyword(String keyword, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<ClassCourseDTO> pagedResult = classRepoPaginate.findAll(paging).map(ClassCourseDTO::new);
        if (keyword != null) {
            return classRepoPaginate.findAllByKeyword(keyword,paging).stream().map(ClassCourseDTO::new).collect(Collectors.toList());
        } else {
            return  pagedResult.stream().collect(Collectors.toList());
        }
    }

    @Override
    public void sendStudentCertificate(String email, String code, long batch) throws Exception {

        if(studentRepo.findByEmail(email) != null &&  classBatchRepo.findClassBatchByCourseCodeAndBatch(code,batch) != null){
            ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code,batch);
            Student student = studentRepo.findStudentByClassBatches(classBatch);
            String certificatePath =   CertificateGen.generateCertificate(student.getFirstname() + " " + student.getLastname(),
                    code,
                    classBatch.getCourse().getTitle(),batch);
            emailSenderService.sendCert(email,"Certificate","Congratulation",certificatePath);

        }else{
            throw new CustomDataNotFoundException();
        }
    }
}
