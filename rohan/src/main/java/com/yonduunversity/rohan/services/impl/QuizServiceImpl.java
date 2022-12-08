package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Quiz;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.QuizRepo;
import com.yonduunversity.rohan.services.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    QuizRepo quizRepo;
    ClassBatchRepo classBatchRepo;

    public Quiz addQuizById(Quiz quiz, long id) {
        quiz.setActive(true);
        Optional<ClassBatch> optionalClassBatch = classBatchRepo.findById(id);
        ClassBatch classBatch = optionalClassBatch.get();
        quiz.setClassBatch(classBatch);
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
