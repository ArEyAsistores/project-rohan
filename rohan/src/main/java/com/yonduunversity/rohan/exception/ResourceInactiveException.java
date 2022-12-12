package com.yonduunversity.rohan.exception;

public class ResourceInactiveException extends RuntimeException {
    public ResourceInactiveException(int id) {
        super("The assessment with id " + id + " has been removed from our records");
    }

}
