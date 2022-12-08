package com.yonduunversity.rohan.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_batch")
public class ClassBatch {

    // ClassBatch Id
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Many class to one course;
    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;

    // One class to many quiz
    @JsonIgnore
    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    // One class to many exercise
    @JsonIgnore
    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    private List<Project> exercises;

    // One class to one project
    @OneToOne(mappedBy = "classBatch", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Project project;

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
