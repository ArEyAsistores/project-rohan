package com.yonduunversity.rohan.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.yonduunversity.rohan.models.student.Student;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import com.yonduunversity.rohan.models.Course;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class ClassBatch {

//    @EmbeddedId
//    private ClassBatchId classBatchId;



    // @OneToOne(mappedBy = "project")
    // private Project project;

    // students, foreign key, many to many

    // ex/qui/proj, foreign key, one to many

    // private Course course;
    @Id
    private long batchNumber;



    @ManyToMany(fetch = FetchType.EAGER) // load Database From Role when this user RUN
    private Collection<Student> students = new ArrayList<>();

    @ManyToOne
    @MapsId("code")
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber + course.getCourseCode();
    }

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
    @NonNull
    @Column(name = "is_active")
    private boolean isActive;

}

