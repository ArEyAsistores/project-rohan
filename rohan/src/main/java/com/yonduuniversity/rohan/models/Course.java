package com.yonduuniversity.rohan.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "courseCode", nullable = false, unique = true)
    private int courseCode;

    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    // classes
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Class> classes;
}
