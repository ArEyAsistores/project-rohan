package com.yonduunversity.rohan.models;

import lombok.Data;

@Data
public class ProjectGrade {
    long id;
    int score;
    int min;
    int max;

    public ProjectGrade(long id, int score, int min, int max) {
        this.id = id;
        this.score = score;
        this.min = min;
        this.max = max;
    }
}
