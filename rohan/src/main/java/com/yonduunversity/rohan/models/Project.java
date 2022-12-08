package com.yonduunversity.rohan.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    // Project Id
    @Id
    @Column(name = "id")
    private long id;

    // One project to one classbatch
    @OneToOne
    @MapsId
    @JoinColumn(name = "classBatch_id")
    ClassBatch classBatch;

    // One project to many grades
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Grade> grades;

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
