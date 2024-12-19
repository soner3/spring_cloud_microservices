package net.sonerapp.review.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.sonerapp.review.exception.InvalidInputException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleInvalidInputException(InvalidInputException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.FORBIDDEN, ex, "Invalid Input");
    }

}
