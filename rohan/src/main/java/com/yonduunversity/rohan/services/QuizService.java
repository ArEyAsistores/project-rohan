package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Quiz;

public interface QuizService {
    Quiz addQuizById(Quiz quiz, long id);

    void removeQuiz(int id);
}
