package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Exercise;

public interface ExerciseService {
    Exercise addExerciseById(Exercise exercise, long id);

    Exercise addExercise(Exercise exercise, String code, long id);

    void removeExercise(int id);

}
