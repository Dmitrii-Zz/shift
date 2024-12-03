package ru.shift.view;

import ru.shift.client.Client;
import ru.shift.client.WriterListener;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TypeMessage;
import ru.shift.utils.Mapper;

public class NameWindowListenerImpl implements NameWindowListener {
    private final WriterListener writerListener;
    private final Client client;

    public NameWindowListenerImpl(WriterListener writerListener, Client client) {
        this.writerListener = writerListener;
        this.client = client;
    }

    @Override
    public void sendName(String name) {
        MessageDto messageDto = new MessageDto(TypeMessage.RESPONSE_NAME, name);
        String jsonMessageDto = Mapper.serializationToJson(messageDto);
        writerListener.send(jsonMessageDto);
        client.setName(name);
    }
}