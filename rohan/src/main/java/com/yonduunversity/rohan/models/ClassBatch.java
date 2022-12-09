package com.yonduunversity.rohan.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yonduunversity.rohan.models.student.Student;
import jakarta.persistence.*;
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
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class ClassBatch implements Serializable {

    // ClassBatch Id
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Many class to one course;
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Course course;

    // One class to many quiz
    @JsonIgnore
    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    // One class to many exercise
    @JsonIgnore
    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    // One class to one project
    @OneToOne(mappedBy = "classBatch", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @NonNull
    private Project project;

    @ManyToOne
    private User sme;

    @ManyToMany
    private Collection<Student> students = new ArrayList<>();

    @Column(name = "quiz_percentage")
    private int quizPercentage;

    @Column(name = "exercise_percentage")
    private int exercisePercentage;

    @Column(name = "project_percentage")
    private int projectPercentage;

    @Column(name = "attendance_percentage")
    private int attendancePercentage;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private boolean isActive;

}
