package com.yonduunversity.rohan.models;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import com.yonduunversity.rohan.models.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class Class {

    @NonNull
    @Column(name = "batch")
    private int batch;

    @NonNull
    @Column(name = "quizPercentage")
    private int quizPercentage;

    @NonNull
    @Column(name = "exercisePercentage")
    private int exercisePercentage;

    @NonNull
    @Column(name = "projectPercentage")
    private int projectPercentage;

    @NonNull
    @Column(name = "attendancePercentage")
    private int attendancePercentage;

    @NonNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @NonNull
    @Column(name = "endDate")
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "courseCode", referencedColumnName = "courseCode")
    private Course course;

    // students, foreign key, many to many
    // ex/qui/proj, foreign key, one to many
}
