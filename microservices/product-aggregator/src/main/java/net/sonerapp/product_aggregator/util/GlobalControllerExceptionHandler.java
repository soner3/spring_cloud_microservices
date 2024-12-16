package net.sonerapp.product_aggregator.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.sonerapp.product_aggregator.exception.InvalidInputException;
import net.sonerapp.product_aggregator.exception.NotFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleInvalidInputException(InvalidInputException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.FORBIDDEN, ex, "Invalid Input");
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleNotFoundException(NotFoundException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.NOT_FOUND, ex, "Not Found");
    }

}
