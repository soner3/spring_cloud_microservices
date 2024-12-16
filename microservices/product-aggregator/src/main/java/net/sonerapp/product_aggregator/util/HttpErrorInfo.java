package net.sonerapp.product_aggregator.util;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

public class HttpErrorInfo {

    public static ResponseEntity<ProblemDetail> errorInfo(HttpStatus status, Exception exception, String title) {
        String exceptionClassPath = exception.getClass().getName().replace(".", "/");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(exceptionClassPath));
        return ResponseEntity.of(problemDetail).build();
    }

}
