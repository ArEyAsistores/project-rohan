package com.yonduunversity.rohan.exception;

public class ClassBatchNotFoundException extends RuntimeException {
    public ClassBatchNotFoundException(String code, long id) {
        super("The class batch of course " + code + " and id " + id + " was not found.");
    }
}
