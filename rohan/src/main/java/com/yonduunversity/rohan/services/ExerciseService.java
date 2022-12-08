package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Exercise;

public interface ExerciseService {
    Exercise addExerciseById(Exercise exercise, long id);

    void removeExercise(int id);

}
