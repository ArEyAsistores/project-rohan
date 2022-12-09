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
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    // @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    // @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    private boolean isActive;

}
