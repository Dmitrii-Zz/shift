package ru.shift.exceptions;

public class FileNotFoundMyException extends RuntimeException {
    public FileNotFoundMyException(String message) {
        super(message);
    }
}
