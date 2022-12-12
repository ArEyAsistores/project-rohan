package com.yonduunversity.rohan.models.dto;

import com.yonduunversity.rohan.models.Course;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data

public class CourseClassDTO {

    private String code;
    private String title;
    private String description;
    private boolean isActive;
    private List<ClassDTO> batches;

    public CourseClassDTO(Course course) {
        this.code = course.getCode();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.isActive = course.isActive();
        batches = course.getClassBatches().stream().map(ClassDTO::new).collect(Collectors.toList());

    }

}

