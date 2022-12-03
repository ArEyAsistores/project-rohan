package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}