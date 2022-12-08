package com.yonduunversity.rohan.models;

import java.io.Serializable;

import com.yonduunversity.rohan.models.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClassBatchId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Course course;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long batch;

}

