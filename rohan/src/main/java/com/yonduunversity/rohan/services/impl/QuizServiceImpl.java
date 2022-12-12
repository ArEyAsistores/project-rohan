package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.exception.ClassBatchNotFoundException;
import com.yonduunversity.rohan.exception.QuizNotFoundException;
import com.yonduunversity.rohan.exception.StudentAlreadyGradedException;
import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Grade;
import com.yonduunversity.rohan.models.Quiz;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.GradeRepo;
import com.yonduunversity.rohan.repository.QuizRepo;
import com.yonduunversity.rohan.services.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    QuizRepo quizRepo;
    ClassBatchRepo classBatchRepo;
    GradeRepo gradeRepo;

    public Quiz addQuizById(Quiz quiz, long id) {
        quiz.setActive(true);
        Optional<ClassBatch> optionalClassBatch = classBatchRepo.findById(id);
        ClassBatch classBatch = optionalClassBatch.get();
        quiz.setClassBatch(classBatch);
        return quizRepo.save(quiz);
    }

    public Quiz addQuiz(Quiz quiz, String code, long batch) {
        quiz.setActive(true);
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, batch);
        if (classBatch == null) {
            throw new ClassBatchNotFoundException(code, batch);
        }
        quiz.setClassBatch(classBatch);
        return quizRepo.save(quiz);
    }

    public Quiz removeQuiz(int id) {
        Quiz quiz = QuizServiceImpl.unwrapQuiz(quizRepo.findById(id), id);
        Optional<Grade> grade = gradeRepo.findByExerciseId(id);
        if (grade.isPresent()) {
            throw new StudentAlreadyGradedException();
        } else {
            if (quiz.isActive()) {
                quiz.setActive(false);
            }
            return quizRepo.save(quiz);
        }
    }

    public static Quiz unwrapQuiz(Optional<Quiz> entity, int id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new QuizNotFoundException(id);
    }
}
