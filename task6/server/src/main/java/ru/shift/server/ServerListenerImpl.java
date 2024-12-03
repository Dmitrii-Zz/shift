package ru.shift.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TypeMessage;
import ru.shift.utils.Mapper;

import java.net.Socket;

public class ServerListenerImpl implements ServerListener {
    private static final Logger log = LoggerFactory.getLogger(ServerListenerImpl.class);

    @Override
    public synchronized void connectionClient(Socket socket) {
        MessageDto requestName = new MessageDto(TypeMessage.REQUEST_NAME);
        String jsonRequestName = Mapper.serializationToJson(requestName);
        log.info("Requesting a name from port '{}", socket.getPort());
        Server.unregisteredClients.get(socket.getPort()).send(jsonRequestName);
    }
}