package ru.shift.dto;

import java.time.LocalDateTime;

public class TextDto {
    private LocalDateTime dateTime;
    private String userName;
    private String message;

    public TextDto() {
    }

    public TextDto(LocalDateTime dateTime, String userName, String message) {
        this.dateTime = dateTime;
        this.userName = userName;
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
