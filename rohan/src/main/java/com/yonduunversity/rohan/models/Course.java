package com.yonduunversity.rohan.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "course")
public class Course {
    @NonNull
    @Id
    @Column(name = "course_code", nullable = false, unique = true)
    private int courseCode;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<ClassBatch> classBatches;


    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

}
