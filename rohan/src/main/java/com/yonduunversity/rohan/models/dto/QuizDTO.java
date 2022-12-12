package com.yonduunversity.rohan.models.dto;

import java.time.LocalDate;

import com.yonduunversity.rohan.models.Quiz;
import lombok.Data;

@Data
public class QuizDTO {
    private int id;
    private String code;
    private long batch;
    private String title;
    private int minScore;
    private int maxScore;
    private LocalDate date;
    private boolean isActive;

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.code = quiz.getClassBatch().getCourse().getCode();
        this.batch = quiz.getClassBatch().getBatch();
        this.title = quiz.getTitle();
        this.minScore = quiz.getMinScore();
        this.maxScore = quiz.getMaxScore();
        this.date = quiz.getDate();
        this.isActive = quiz.isActive();
    }

}
