package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.ClassBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findStudentByClassBatches(ClassBatch classBatch);
}