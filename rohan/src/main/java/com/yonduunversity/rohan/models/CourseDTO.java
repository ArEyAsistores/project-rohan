package com.yonduunversity.rohan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class CourseDTO {
    private String code;
    private String title;
    private String description;
    private boolean isActive;

    public CourseDTO(Course course){
        this.code =course.getCode();
        this.title = course.getTitle();
        this.description= course.getDescription();
        this.isActive = course.isActive();
    }

}

