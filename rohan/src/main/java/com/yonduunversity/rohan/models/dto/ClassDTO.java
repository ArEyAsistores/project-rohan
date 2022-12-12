package com.yonduunversity.rohan.models.dto;

import com.yonduunversity.rohan.models.ClassBatch;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassDTO {


    private long batch;
    private UserDTO sme;
    private int quizPercentage;
    private int exercisePercentage;
    private int projectPercentage;
    private int attendancePercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private  boolean isActive;

    public ClassDTO(ClassBatch classBatch){
        this.batch =  classBatch.getBatch();
        this.quizPercentage = classBatch.getQuizPercentage();
        this.exercisePercentage = classBatch.getExercisePercentage();
        this.projectPercentage = classBatch.getProjectPercentage();
        this.attendancePercentage = classBatch.getAttendancePercentage();
        this.startDate = classBatch.getStartDate();
        this.endDate = classBatch.getEndDate();
        this.isActive = classBatch.isActive();
        this.sme = new UserDTO(classBatch.getSme());
    }

}

