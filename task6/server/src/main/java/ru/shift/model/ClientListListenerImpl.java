package ru.shift.model;

import ru.shift.dto.MessageDto;
import ru.shift.dto.TypeMessage;
import ru.shift.server.Server;
import ru.shift.utils.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientListListenerImpl implements ClientListListener {

    @Override
    public synchronized void sendChangesClientList() {

        Set<String> clientNames = Server.registeredClients.values().stream()
                .map(Client::getName)
                .collect(Collectors.toSet());

        String jsonClientNames = Mapper.serializationToJson(clientNames);

        MessageDto messageDto = new MessageDto(TypeMessage.NAME_CLIENTS, jsonClientNames);
        String jsonMessageDto = Mapper.serializationToJson(messageDto);

        for (Client client : Server.registeredClients.values()) {
            client.send(jsonMessageDto);
        }
    }
}