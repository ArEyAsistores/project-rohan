// package com.yonduunversity.rohan.models;

// import java.time.LocalDate;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.*;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "quiz")
// public class Quiz {
// @Id
// @NonNull
// @Column(name = "id", unique = true)
// @GeneratedValue(strategy = GenerationType.AUTO)
// private int id;

// // many to one to classbatch
// // many to one to student

// @NonNull
// @Column(name = "course_code")
// private int courseCode;

// @NonNull
// @Column(name = "batch")
// private int batch;
// // student

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
