package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.exception.TotalGradePercentageInvalidException;
import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Project;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.CourseRepo;
import com.yonduunversity.rohan.repository.ProjectRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.services.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public ClassBatch saveClass(ClassBatch classBatch, String whoAdded) throws Exception {
        Course course = courseRepo.findCourseByCode(classBatch.getCourse().getCode());
        User userSme = userRepo.findByEmail(whoAdded);

        int totalPercentage = classBatch.getExercisePercentage() + classBatch.getQuizPercentage()
                + classBatch.getAttendancePercentage() + classBatch.getProjectPercentage();
        if (totalPercentage == 100) {
            classBatch.setCourse(course);
            classBatch.setSme(userSme);
            classBatch.setBatch(classBatchRepo.findClassBatchByCourseCode(course.getCode()) + 1);
            Project project = new Project();
            project.setClassBatch(classBatch);
            projectRepo.save(project);
            classBatch.setProject(project);

            classBatchRepo.save(classBatch);
            course.getClassBatches().add(classBatch);
            courseRepo.save(course);
            return classBatch;
        } else {
            throw new TotalGradePercentageInvalidException();
        }

    }

    public Course viewCoursesWithClasses(String code, long batch) {

        return null;
    }

    @Override
    public ClassBatch enrollStudent(String email, String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
        Student studentEnrolled = studentRepo.findByEmail(email);
        studentEnrolled.setActive(true);
        classBatch.getStudents().add(studentEnrolled);
        studentEnrolled.getClassBatches().add(classBatch);
        studentRepo.save(studentEnrolled);
        return classBatchRepo.save(classBatch);
    }

    @Override
    public ClassBatch unEnrollStudent(String email, String code, long batchNumber) {
        if (classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber) != null
                || studentRepo.findByEmail(email) != null) {
            ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
            Student studentUnEnroll = studentRepo.findByEmail(email);
            classBatch.getStudents().remove(studentUnEnroll);
            studentUnEnroll.getClassBatches().remove(classBatch);
            studentRepo.save(studentUnEnroll);
            classBatch.setActive(false);
            return classBatchRepo.save(classBatch);
        }
        return null;
    }

    @Override
    public ClassBatch deactivateClass(String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
        classBatch.getStudents().removeAll(classBatch.getStudents());
        classBatch.setActive(false);
        return classBatchRepo.save(classBatch);
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
    public ClassBatch getClassStudents(String code, long batchNumber) {
        return classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batchNumber);
    }
}
