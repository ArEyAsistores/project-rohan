package com.yonduunversity.rohan.models;

import lombok.Data;

@Data
public class GradeDTO {
    private String code;
    private long batch;
    private int quiz_id;
    private int exercise_id;
    private long project_id;
    private String email;
    private int score;

    public GradeDTO(Grade grade) {
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
    }
}
