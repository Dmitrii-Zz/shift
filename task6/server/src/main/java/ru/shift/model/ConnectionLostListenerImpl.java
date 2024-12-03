package ru.shift.model;

import ru.shift.dto.MessageDto;
import ru.shift.dto.TypeMessage;
import ru.shift.server.Server;
import ru.shift.utils.Mapper;

public class ConnectionLostListenerImpl implements ConnectionLostListener {

    private final ClientListListener clientListListener;

    public ConnectionLostListenerImpl(ClientListListener clientListListener) {
        this.clientListListener = clientListListener;
    }

    @Override
    public synchronized void notifyConnectionLost(int port) {
        String nameClient = Server.registeredClients.remove(port).getName();
        String strMessage = nameClient + " отключен(а) от чата";
        MessageDto messageDto = new MessageDto(TypeMessage.INFO_MESSAGE, strMessage);
        String jsonMessage = Mapper.serializationToJson(messageDto);

        for (Client client : Server.registeredClients.values()) {
            client.send(jsonMessage);
        }

        clientListListener.sendChangesClientList();
    }
}