package com.yonduunversity.rohan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yonduunversity.rohan.exception.ErrorResponse;
import com.yonduunversity.rohan.exception.ExerciseNotFoundException;
import com.yonduunversity.rohan.exception.GradeNotFoundException;
import com.yonduunversity.rohan.exception.GradeOutOfBoundException;
import com.yonduunversity.rohan.exception.ProjectNotFoundException;
import com.yonduunversity.rohan.exception.QuizNotFoundException;
import com.yonduunversity.rohan.exception.TotalGradePercentageInvalidException;
import com.yonduunversity.rohan.models.Grade;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = GradeOutOfBoundException.class)
    public @ResponseBody ErrorResponse handleGradeOutOfBounds(GradeOutOfBoundException e) {
        return new ErrorResponse(422, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = QuizNotFoundException.class)
    public @ResponseBody ErrorResponse handleQuizNotFound(QuizNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ExerciseNotFoundException.class)
    public @ResponseBody ErrorResponse handleExerciseNotFound(ExerciseNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ProjectNotFoundException.class)
    public @ResponseBody ErrorResponse handleProjectNotFound(ProjectNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = GradeNotFoundException.class)
    public @ResponseBody ErrorResponse handleGradeNotFound(GradeNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = TotalGradePercentageInvalidException.class)
    public @ResponseBody ErrorResponse handletotalGradePercentageInvalid(TotalGradePercentageInvalidException e) {
        return new ErrorResponse(422, e.getMessage());
    }

}
