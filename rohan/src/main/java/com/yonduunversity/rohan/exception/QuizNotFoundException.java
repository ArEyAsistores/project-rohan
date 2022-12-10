package com.yonduunversity.rohan.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(int id) {
        super("The quiz id " + id + "does not exist in our records.");
    }
}
