package com.yonduuniversity.rohan.models;

import com.yonduuniversity.rohan.models.Course;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Class {

    @ManyToOne(optional = false)
    @JoinColumn(name = "courseCode", referencedColumnName = "courseCode")
    private Course course;
}
