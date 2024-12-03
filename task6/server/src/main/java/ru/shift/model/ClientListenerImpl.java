package ru.shift.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TypeMessage;
import ru.shift.server.Server;
import ru.shift.utils.Mapper;

public class ClientListenerImpl implements ClientListener {
    private static final Logger log = LoggerFactory.getLogger(ClientListenerImpl.class);
    private final ClientListListener clientListListener;

    public ClientListenerImpl(ClientListListener clientListListener) {
        this.clientListListener = clientListListener;
    }

    @Override
    public synchronized void handleMessage(String request, int port) {
        MessageDto messageDto = Mapper.deserializationMessFromJson(request);

        switch (messageDto.getTypeMessage()) {
            case RESPONSE_NAME -> registerClient(messageDto.getMessage(), port);
            case MESSAGE -> processMessage(messageDto, port);
        }
    }

    private synchronized void registerClient(String name, int port) {
        Client client = Server.unregisteredClients.get(port);

        if (checkName(name)) {
            log.info("name '{}' on the server is taken", name);
            MessageDto messageDto = new MessageDto(TypeMessage.WRONG_NAME);
            String jsonMessageDto = Mapper.serializationToJson(messageDto);
            client.send(jsonMessageDto);
            return;
        }

        client.setName(name);

        Server.unregisteredClients.remove(port);
        Server.registeredClients.put(port, client);

        log.info("Client '{}' is registered", name);

        clientListListener.sendChangesClientList();
        sendRegisteredClient(port);
        sendInfoMessConnectClient(name);
    }

    private synchronized boolean checkName(String name) {
        return Server.registeredClients.values().stream()
                .filter(c -> c.getName() != null)
                .anyMatch(c -> c.getName().equals(name));
    }

    private synchronized void sendRegisteredClient(int port) {
        MessageDto messageDto = new MessageDto(TypeMessage.REGISTERED);
        String message = Mapper.serializationToJson(messageDto);
        Server.registeredClients.get(port).send(message);
    }

    private synchronized void sendInfoMessConnectClient(String name) {
        String strMessage = name + " подключен(а) к чату";
        MessageDto messageDto = new MessageDto(TypeMessage.INFO_MESSAGE, strMessage);
        String jsonMessageDto = Mapper.serializationToJson(messageDto);

        for (Client client : Server.registeredClients.values()) {
            client.send(jsonMessageDto);
        }
    }

    private synchronized void processMessage(MessageDto messageDto, int port) {
        Client registeredclient = Server.registeredClients.get(port);

        if (registeredclient == null) {
            log.info("client port: '{}' not registered", port);
            return;
        }

        String jsonMessage = Mapper.serializationToJson(messageDto);
        for (Client client : Server.registeredClients.values()) {
            client.send(jsonMessage);
        }
    }
}