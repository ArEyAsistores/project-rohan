package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.exception.ExerciseNotFoundException;
import com.yonduunversity.rohan.exception.StudentAlreadyGradedException;
import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Exercise;
import com.yonduunversity.rohan.models.Grade;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.ExerciseRepo;
import com.yonduunversity.rohan.repository.GradeRepo;
import com.yonduunversity.rohan.services.ExerciseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    ExerciseRepo exerciseRepo;
    ClassBatchRepo classBatchRepo;
    GradeRepo gradeRepo;

    public Exercise addExerciseById(Exercise exercise, long id) {
        exercise.setActive(true);
        Optional<ClassBatch> optionalClassBatch = classBatchRepo.findById(id);
        ClassBatch classBatch = optionalClassBatch.get();
        exercise.setClassBatch(classBatch);
        return exerciseRepo.save(exercise);
    }

    public void removeExercise(int id) {
        Exercise exercise = ExerciseServiceImpl.unwrapExercise(exerciseRepo.findById(id), id);
        Optional<Grade> grade = gradeRepo.findByExerciseId(id);
        if (grade.isPresent()) {
            throw new StudentAlreadyGradedException();
        } else {
            if (exercise.isActive()) {
                exercise.setActive(false);
            }
            exerciseRepo.save(exercise);
        }

    }

    public Exercise addExercise(Exercise exercise, String code, long id) {
        exercise.setActive(true);
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndBatch(code, id);
        exercise.setClassBatch(classBatch);
        return exerciseRepo.save(exercise);
    }

    static Exercise unwrapExercise(Optional<Exercise> entity, int id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new ExerciseNotFoundException(id);
    }
}
