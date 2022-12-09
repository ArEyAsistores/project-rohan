package com.yonduunversity.rohan.models;

import com.yonduunversity.rohan.models.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    // Grade id
    @Id
    @NonNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Many grade to one classbatch
    @ManyToOne
    @JoinColumn(name = "classbatch_id", referencedColumnName = "id")
    private ClassBatch classBatch;

    // Many grade to one student
    @ManyToOne
    @JoinColumn(name = "student_email", referencedColumnName = "id")
    @NonNull
    private Student student;

    // Many grade to one quiz
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    // Many grade to one project
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    // many grade to one exercise
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private Exercise exercise;

    private int score;

}
