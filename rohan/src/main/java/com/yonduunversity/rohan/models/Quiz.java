package com.yonduunversity.rohan.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yonduunversity.rohan.models.student.Student;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
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

    // many to one to classbatch composite key
    @MapsId("classBatchId")
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "batch", referencedColumnName = "batch") })
    private ClassBatch classBatch;

    @JsonIgnore
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Grade> grades;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "max_score")
    private int maxScore;

    @NonNull
    @Column(name = "min_score")
    private int minScore;

    @NonNull
    @Column(name = "date")
    private LocalDate date;

    @NonNull
    @Column(name = "active")
    private boolean isActive;
}
