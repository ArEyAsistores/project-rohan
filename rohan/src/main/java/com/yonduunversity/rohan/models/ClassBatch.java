package com.yonduunversity.rohan.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yonduunversity.rohan.models.student.Student;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import com.yonduunversity.rohan.models.Course;

import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class ClassBatch implements Serializable {




//    @EmbeddedId
//    ClassBatchId classBatchId;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long batch;
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Course course;

    @ManyToOne
    private User sme;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonIgnore

    @ManyToMany // load Database From Role when this user RUN
    private Collection<Student> students = new ArrayList<>();

    @Column(name = "quiz_percentage")
    private int quizPercentage;

    @Column(name = "exercise_percentage")
    private int exercisePercentage;

    @Column(name = "project_percentage")
    private int projectPercentage;

    @Column(name = "attendance_percentage")
    private int attendancePercentage;


    @Column(name = "start_date")
    private LocalDate startDate;


    @Column(name = "end_date")
    private LocalDate endDate;

    private  boolean isActive;

}

