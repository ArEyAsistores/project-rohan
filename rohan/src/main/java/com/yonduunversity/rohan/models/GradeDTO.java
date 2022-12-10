package com.yonduunversity.rohan.models;

import lombok.Data;

@Data
public class GradeDTO {
    private long id;
    private String code;
    private long batch;
    private String email;
    private int quiz_id;
    private int exercise_id;
    private long project_id;
    private int score;
    private String combination;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.code = grade.getClassBatch().getCourse().getCode();
        this.batch = grade.getClassBatch().getBatch();
        this.email = grade.getStudent().getEmail();
        if (grade.getQuiz() != null) {
            this.quiz_id = grade.getQuiz().getId();
        }
        if (grade.getExercise() != null) {
            this.exercise_id = grade.getExercise().getId();
        }
        if (grade.getProject() != null) {
            this.project_id = grade.getProject().getId();
        }

        this.score = grade.getScore();
        this.combination = grade.getCombination();
    }

    // public GradeDTO(Grade grade, Exercise exercise) {
    // this.id = grade.getId();
    // this.code = grade.getClassBatch().getCourse().getCode();
    // this.batch = grade.getClassBatch().getBatch();
    // this.email = grade.getStudent().getEmail();
    // this.exercise_id = exercise.getId();
    // this.score = grade.getScore();
    // this.combination = grade.getCombination();
    // }

    // public GradeDTO(Grade grade, Project project) {
    // this.id = grade.getId();
    // this.code = grade.getClassBatch().getCourse().getCode();
    // this.batch = grade.getClassBatch().getBatch();
    // this.email = grade.getStudent().getEmail();
    // this.project_id = project.getId();
    // this.score = grade.getScore();
    // this.combination = grade.getCombination();
    // }
}
