package com.yonduunversity.rohan.services.impl;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.models.Quiz;
import com.yonduunversity.rohan.repository.QuizRepo;
import com.yonduunversity.rohan.services.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    QuizRepo quizRepo;

    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }
}
