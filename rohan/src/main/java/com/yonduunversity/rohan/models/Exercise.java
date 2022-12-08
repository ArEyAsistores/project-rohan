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
@Table(name = "exercise")
public class Exercise {
    @Id
    @NonNull
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "classbatch_id", referencedColumnName = "id")
    private ClassBatch classBatch;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
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
