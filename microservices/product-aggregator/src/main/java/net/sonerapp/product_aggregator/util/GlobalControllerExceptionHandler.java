package net.sonerapp.product_aggregator.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        return HttpErrorInfo.errorInfo(HttpStatus.FORBIDDEN, ex, "Mismatched Method Argument Type");
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> handleRestClientException(RestClientException ex) {

        if (ex instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
            ProblemDetail problemDetail = httpClientErrorException.getResponseBodyAs(ProblemDetail.class);
            return ResponseEntity.of(problemDetail).build();
        }
        return HttpErrorInfo.errorInfo(HttpStatus.INTERNAL_SERVER_ERROR, ex, "Rest Client Error");
    }

}
