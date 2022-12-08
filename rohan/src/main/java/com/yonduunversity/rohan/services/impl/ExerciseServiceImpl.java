package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Exercise;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.ExerciseRepo;
import com.yonduunversity.rohan.services.ExerciseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    ExerciseRepo exerciseRepo;
    ClassBatchRepo classBatchRepo;

    public Exercise addExerciseById(Exercise exercise, long id) {
        exercise.setActive(true);
        Optional<ClassBatch> optionalClassBatch = classBatchRepo.findById(id);
        ClassBatch classBatch = optionalClassBatch.get();
        exercise.setClassBatch(classBatch);
        return exerciseRepo.save(exercise);
    }

    public void removeExercise(int id) {
        Optional<Exercise> optionalExercise = exerciseRepo.findById(id);
        Exercise exercise = optionalExercise.get();
        if (exercise.isActive()) {
            exercise.setActive(false);
        }
    }
}
