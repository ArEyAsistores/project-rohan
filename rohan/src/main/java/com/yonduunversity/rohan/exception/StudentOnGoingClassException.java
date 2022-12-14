package com.yonduunversity.rohan.exception;

public class StudentOnGoingClassException extends RuntimeException {
    public StudentOnGoingClassException(String code, long batch) {
        super("This student is currently enrolled in this class: " + batch + " - " + code);
    }

}
