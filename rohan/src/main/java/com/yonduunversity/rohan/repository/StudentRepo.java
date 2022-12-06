package com.yonduuniversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduuniversity.rohan.models.User;
import com.yonduuniversity.rohan.models.student.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}