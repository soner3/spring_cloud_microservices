package net.sonerapp.product.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
