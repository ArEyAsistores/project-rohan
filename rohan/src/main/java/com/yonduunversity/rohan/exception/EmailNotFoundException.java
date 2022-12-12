package com.yonduunversity.rohan.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email does not exist in our records.");
    }
}
