package com.yonduunversity.rohan.exception;

import com.yonduunversity.rohan.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice { @ExceptionHandler(NullPointerException.class)

public ResponseEntity<com.yonduunversity.rohan.models.ErrorResponse> handleNullPointerExceptions(Exception e) { // 404
    HttpStatus status = HttpStatus.NOT_FOUND;
    String stackTrace = stackTraceMessage(e);
    return new ResponseEntity<>(new com.yonduunversity.rohan.models.ErrorResponse(status,e.getMessage()), status);
}
    @ExceptionHandler(CustomParameterConstraintException.class)
    public ResponseEntity<com.yonduunversity.rohan.models.ErrorResponse> handleRestClientException(Exception e) { // 400
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new com.yonduunversity.rohan.models.ErrorResponse(status,e.getMessage()), status);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.yonduunversity.rohan.models.ErrorResponse> handleExceptions(Exception e) { // 500
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String stackTrace = stackTraceMessage(e);
        return new ResponseEntity<>(new ErrorResponse(status,e.getMessage(),stackTrace), status);
    }
    public String stackTraceMessage(Exception e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
