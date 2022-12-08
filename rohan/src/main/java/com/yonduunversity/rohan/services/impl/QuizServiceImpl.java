package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

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
        quiz.setActive(true);
        return quizRepo.save(quiz);
    }

    public void removeQuiz(int id) {
        Optional<Quiz> optionalQuiz = quizRepo.findById(id);
        Quiz quiz = optionalQuiz.get();
        if (quiz.isActive()) {
            quiz.setActive(false);
        }
    }
}
