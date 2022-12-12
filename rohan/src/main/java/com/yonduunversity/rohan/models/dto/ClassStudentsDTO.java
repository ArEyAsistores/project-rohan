package com.yonduunversity.rohan.models.dto;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClassStudentsDTO {


    private long batch;
    private UserDTO sme;

    private Course course;
    private int quizPercentage;
    private int exercisePercentage;
    private int projectPercentage;
    private int attendancePercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private  boolean isActive;

    private List<UserDTO> students;

    public ClassStudentsDTO(ClassBatch classBatch){
        this.batch =  classBatch.getBatch();
        this.quizPercentage = classBatch.getQuizPercentage();
        this.exercisePercentage = classBatch.getExercisePercentage();
        this.projectPercentage = classBatch.getProjectPercentage();
        this.attendancePercentage = classBatch.getAttendancePercentage();
        this.startDate = classBatch.getStartDate();
        this.endDate = classBatch.getEndDate();
        this.isActive = classBatch.isActive();
        this.students = classBatch.getStudents().stream().map(UserDTO::new).collect(Collectors.toList());
        this.sme = new UserDTO(classBatch.getSme());
        this.course = classBatch.getCourse();
    }

}

