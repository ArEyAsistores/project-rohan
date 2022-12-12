package com.yonduunversity.rohan.models.dto;

import java.time.LocalDate;

import com.yonduunversity.rohan.models.Exercise;
import lombok.Data;

@Data
public class ExerciseDTO {
    private int id;
    private String code;
    private long batch;
    private String title;
    private int minScore;
    private int maxScore;
    private LocalDate date;
    private boolean isActive;

    public ExerciseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.code = exercise.getClassBatch().getCourse().getCode();
        this.batch = exercise.getClassBatch().getBatch();
        this.title = exercise.getTitle();
        this.minScore = exercise.getMinScore();
        this.maxScore = exercise.getMaxScore();
        this.date = exercise.getDate();
        this.isActive = exercise.isActive();
    }
}
