package ru.shift.exceptions;

public class FailedCreateFileException extends RuntimeException {
    public FailedCreateFileException(String message) {
        super(message);
    }
}
