package ru.shift.exceptions;

public class InvalidInputParamException extends RuntimeException {
    public InvalidInputParamException(String message) {
        super(message);
    }
}
