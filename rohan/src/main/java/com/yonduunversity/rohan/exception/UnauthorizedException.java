package com.yonduunversity.rohan.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String user) {
        super("User " + user + " does not have authorization to access this resource.");
    }
}
