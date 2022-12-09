package com.yonduunversity.rohan.models;

import com.yonduunversity.rohan.models.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class ClassBatchDTO {


    private long batch;
    private Course course;
    private User sme;
    private int quizPercentage;
    private int exercisePercentage;
    private int projectPercentage;
    private int attendancePercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private  boolean isActive;

}

