package com.yonduunversity.rohan.exception;

public class UnauthenticatedAccessException extends RuntimeException {
    public UnauthenticatedAccessException(String user) {
        super("User " + user + " does not have authentication to access this resource.");
    }
}
