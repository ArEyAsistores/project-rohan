package com.yonduunversity.rohan.exception;

public class GradeOutOfBoundException extends RuntimeException {
    public GradeOutOfBoundException(int score, int min, int max) {
        super("The score " + score + " does not fall between the minimum score " + min + " and maximum score " +
                max + "of this assessment");
    }
}
