package com.yonduunversity.rohan.services;

import java.util.List;

import com.yonduunversity.rohan.models.ClassBatchId;
import com.yonduunversity.rohan.models.Grade;

public interface GradeService {
    // List<Grade> retrieveStudentGrades(String email);

    // List<Grade> retrieveClassGrades(ClassBatchId id);

    Grade giveQuizScore(int quiz_id, String email, int score);

    Grade giveExerciseScore(int exercise_id, String email, int score);

    Grade giveProjectScore(int project_id, String email, int score);
}
