package com.yonduunversity.rohan.services;

import java.util.List;
import com.yonduunversity.rohan.models.Grade;

public interface GradeService {
    List<Grade> retrieveStudentGrades(String email, String code, long batch);

    List<Grade> retrieveClassGrades(String code, long batch);

    Grade giveQuizScore(int quiz_id, String email, int score);

    Grade giveExerciseScore(int exercise_id, String email, int score);

    Grade giveProjectScore(String code, long batch, String email, int score);
}
