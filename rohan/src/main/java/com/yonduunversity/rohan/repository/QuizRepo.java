package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
