package com.yonduunversity.rohan.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
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
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)// load Database From Role when this user RUN
    @JsonIgnore

    private Collection<ClassBatch> classBatches = new ArrayList<>();

}
