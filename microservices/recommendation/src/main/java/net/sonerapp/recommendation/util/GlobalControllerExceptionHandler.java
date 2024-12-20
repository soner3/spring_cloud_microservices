package net.sonerapp.recommendation.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import net.sonerapp.recommendation.exception.NotFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.FORBIDDEN, ex, "Mismatched Method Argument Type");
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleNotFoundException(NotFoundException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.NOT_FOUND, ex, "Not Found");
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return HttpErrorInfo.errorInfoMethodArguments(HttpStatus.BAD_REQUEST, ex, "Invalid Method Arguments");
    }

}
