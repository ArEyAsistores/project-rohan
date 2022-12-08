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

    @Id
    @NonNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Many to one quiz
    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    // Many to one student
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @NonNull
    private Student student;

    // many to one classbatch
    @MapsId("classBatchId")
    @ManyToOne
    @NonNull
    @JoinColumns({ @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "batch", referencedColumnName = "batch") })
    private ClassBatch classBatch;

    // many to one project

    // many to one exercise

    private int score;

}
