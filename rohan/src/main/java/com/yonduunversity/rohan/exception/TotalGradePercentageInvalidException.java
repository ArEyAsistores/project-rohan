package com.yonduunversity.rohan.exception;

public class TotalGradePercentageInvalidException extends RuntimeException {
    public TotalGradePercentageInvalidException() {
        super("The assessment weighting percentages do not add up to 100.");
    }
}
