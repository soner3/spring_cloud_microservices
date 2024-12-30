package net.sonerapp.product_aggregator.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class HttpErrorInfo {

    public static ResponseEntity<ProblemDetail> errorInfo(HttpStatus status, Exception exception, String title) {
        String exceptionClassPath = exception.getClass().getName().replace(".", "/");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(exceptionClassPath));
        return ResponseEntity.of(problemDetail).build();
    }

    public static ResponseEntity<ProblemDetail> errorInfoMethodArguments(HttpStatus status,
            MethodArgumentNotValidException exception, String title) {
        String exceptionClassPath = exception.getClass().getName().replace(".", "/");
        Map<String, Object> errors = new HashMap<>();

        exception.getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        });

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create(exceptionClassPath));
        problemDetail.setTitle(title);
        problemDetail.setDetail("Field validation failed");
        problemDetail.setProperties(errors);

        return ResponseEntity.of(problemDetail).build();
    }

}
