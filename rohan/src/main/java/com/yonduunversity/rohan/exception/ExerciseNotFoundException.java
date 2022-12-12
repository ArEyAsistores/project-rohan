package com.yonduunversity.rohan.exception;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(int id) {
        super("The exercise id " + id + " does not exist in our records.");
    }

}
