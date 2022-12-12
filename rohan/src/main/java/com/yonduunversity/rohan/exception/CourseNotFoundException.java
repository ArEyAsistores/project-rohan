package com.yonduunversity.rohan.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String code) {
        super("Course " + code + " was not found in our records.");
    }
}
