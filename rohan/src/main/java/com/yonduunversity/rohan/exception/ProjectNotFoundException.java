package com.yonduunversity.rohan.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(long id) {
        super("The project id " + id + "does not exist in our records.");
    }

}
