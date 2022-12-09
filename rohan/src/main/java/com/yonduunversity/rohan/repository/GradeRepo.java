package com.yonduunversity.rohan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Grade;

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> findByStudentEmail(String email);

    List<Grade> findByClassBatchId(long id);

}
