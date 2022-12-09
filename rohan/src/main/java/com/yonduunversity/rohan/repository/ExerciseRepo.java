package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Exercise;

public interface ExerciseRepo extends JpaRepository<Exercise, Integer> {

}
