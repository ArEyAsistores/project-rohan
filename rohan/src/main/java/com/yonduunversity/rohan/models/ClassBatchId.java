package com.yonduunversity.rohan.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClassBatchId implements Serializable {
    // @ManyToOne(optional = false)
    // @MapsId("course_code")
    // @JoinColumn(name = "course_code", referencedColumnName = "course_code")
    private int course_code;

    private int batch;

}

