package com.yonduunversity.rohan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Grade;

public interface GradeRepo extends JpaRepository<Grade, Integer> {
    List<Grade> findByStudentEmail(String email);

    List<Grade> findByStudentEmailAndClassBatchId(String email, long id);

    List<Grade> findByClassBatchId(long id);

    Optional<Grade> findByExerciseId(int id);

    Optional<Grade> findByQuizId(int id);

}
