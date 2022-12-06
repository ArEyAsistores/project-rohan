package com.yonduunversity.rohan.models;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import com.yonduunversity.rohan.models.Course;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_batch")
public class ClassBatch {

    @EmbeddedId
    private ClassBatchId classBatchId;

    // @OneToOne(mappedBy = "project")
    // private Project project;

    // students, foreign key, many to many

    // ex/qui/proj, foreign key, one to many

    // private Course course;
    @ManyToOne
    @MapsId("code")
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;

    @NonNull
    @Column(name = "quiz_percentage")
    private int quizPercentage;

    @NonNull
    @Column(name = "exercise_percentage")
    private int exercisePercentage;

    @NonNull
    @Column(name = "project_percentage")
    private int projectPercentage;

    @NonNull
    @Column(name = "attendance_percentage")
    private int attendancePercentage;

    @NonNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date")
    private LocalDate endDate;

}

