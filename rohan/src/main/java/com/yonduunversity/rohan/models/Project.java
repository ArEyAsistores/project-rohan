// package com.yonduunversity.rohan.models;

// import java.time.LocalDate;

// import jakarta.persistence.Column;
// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.MapsId;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;
// import lombok.*;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "project")
// public class Project {
// @EmbeddedId
// private ClassBatchId classBatchId;

// @OneToOne
// @JoinColumn(name = "course_code", referencedColumnName = "course_code")
// @JoinColumn(name = "batch", referencedColumnName = "batch")
// @NonNull
// @MapsId
// @Column(name = "course_code")
// private Course course;

// @NonNull
// @Column(name = "batch")
// private int batch;

// // one to one to student

// @NonNull
// @Column(name = "title")
// private int title;

// @NonNull
// @Column(name = "max_score")
// private int maxScore;

// @NonNull
// @Column(name = "min_score")
// private int minScore;

// @NonNull
// @Column(name = "score")
// private int score;

// @NonNull
// @Column(name = "date")
// private LocalDate date;
// }
