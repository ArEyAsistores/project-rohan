package com.yonduunversity.rohan.exception;

public class StudentAlreadyGradedException extends RuntimeException {
    public StudentAlreadyGradedException() {
        super("Cannot remove assessment. Assessment currently stores grades of students in database.");
    }

}
