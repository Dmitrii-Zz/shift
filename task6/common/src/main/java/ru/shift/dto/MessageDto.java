package ru.shift.dto;

public class MessageDto {
    private TypeMessage typeMessage;
    private String message;

    public MessageDto(TypeMessage typeMessage, String message) {
        this.typeMessage = typeMessage;
        this.message = message;
    }

    public MessageDto(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public MessageDto() {
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public String getMessage() {
        return message;
    }
}
