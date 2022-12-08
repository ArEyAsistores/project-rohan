package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Grade;

public interface GradeRepo extends JpaRepository<Grade, Integer> {

}
