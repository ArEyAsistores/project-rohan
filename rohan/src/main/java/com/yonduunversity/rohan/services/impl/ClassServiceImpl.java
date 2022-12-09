package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Project;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.CourseRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.services.ClassService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final ClassBatchRepo classBatchRepo;

    @Override
    public ClassBatch saveClass(ClassBatch classBatch, String whoAdded) {
        Course course = courseRepo.findCourseByCode(classBatch.getCourse().getCode());
        User userSme = userRepo.findByEmail(whoAdded);
        classBatch.setCourse(course);
        classBatch.setSme(userSme);
        return classBatchRepo.save(classBatch);
    }

    @Override
    public ClassBatch enrollStudent(String email, String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
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
            ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
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
    public List<ClassBatch> findStudentCourse() {
        Student studentUnEnroll = studentRepo.findByEmail("student2.rey@yahoo.com");
        return studentUnEnroll.getClassBatches().stream().toList();
    }

    @Override
    public List<ClassBatch> getAllClassBatch() {
        return classBatchRepo.findAll();
    }
}
