package ru.shift.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.client.Client;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TextDto;
import ru.shift.dto.TypeMessage;
import ru.shift.utils.Mapper;
import ru.shift.view.TextFieldListener;

import java.time.LocalDateTime;

public class TextFieldController implements TextFieldListener {
    private static final Logger log = LoggerFactory.getLogger(TextFieldController.class);
    private final Client client;

    public TextFieldController(Client client) {
        this.client = client;
    }

    @Override
    public void sendMessage(String message) {
        TextDto textDto = new TextDto(LocalDateTime.now(), client.getName(), message);
        String jsonTextDto = Mapper.serializationToJson(textDto);

        MessageDto messageDto = new MessageDto(TypeMessage.MESSAGE, jsonTextDto);
        String jsonMessageDto = Mapper.serializationToJson(messageDto);

        client.send(jsonMessageDto);
    }
}