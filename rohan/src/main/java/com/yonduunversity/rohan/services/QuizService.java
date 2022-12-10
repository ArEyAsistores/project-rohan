package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Quiz;

public interface QuizService {
    Quiz addQuizById(Quiz quiz, long id);

    Quiz addQuiz(Quiz quiz, String code, long batch);

    void removeQuiz(int id);
}
