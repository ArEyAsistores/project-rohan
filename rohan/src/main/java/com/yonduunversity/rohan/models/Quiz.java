package com.yonduunversity.rohan.models;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yonduunversity.rohan.models.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @NonNull
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // many to many students foreign key
    // @JsonIgnore
    // @ManyToMany
    // @JoinTable(name = "student_quiz", joinColumns = @JoinColumn(name = "quiz_id",
    // referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
    // "student_id", referencedColumnName = "id"))
    // Set<Student> students;

    // many to one to classbatch composite key
    @MapsId("classBatchId")
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "batch", referencedColumnName = "batch") })
    private ClassBatch classBatch;

    @NonNull
    @Column(name = "title")
    private int title;

    @NonNull
    @Column(name = "max_score")
    private int maxScore;

    @NonNull
    @Column(name = "min_score")
    private int minScore;

    @NonNull
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "isActive")
    private boolean isActive;
}
