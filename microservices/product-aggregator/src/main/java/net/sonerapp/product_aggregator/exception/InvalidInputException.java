package net.sonerapp.product_aggregator.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
