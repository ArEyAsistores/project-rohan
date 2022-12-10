package com.yonduunversity.rohan.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(int id) {
        super("No grades for quiz or exercise " + id + " exists.");
    }
}
