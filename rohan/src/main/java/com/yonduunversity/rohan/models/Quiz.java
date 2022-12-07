package com.yonduunversity.rohan.models;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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

    // many to one to classbatch composite key
    @MapsId("classBatchId")
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "batch", referencedColumnName = "batch") })
    private ClassBatch classBatch;

    // @NonNull
    // @Column
    // private long courseCode;

    // @NonNull
    // @Column
    // private int batch;

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
    @Column(name = "score")
    private int score;

    @NonNull
    @Column(name = "date")
    private LocalDate date;
}
