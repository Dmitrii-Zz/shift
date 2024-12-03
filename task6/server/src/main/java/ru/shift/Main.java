package ru.shift;

import ru.shift.model.ClientListListener;
import ru.shift.model.ClientListListenerImpl;
import ru.shift.model.ClientListener;
import ru.shift.model.ClientListenerImpl;
import ru.shift.model.ConnectionLostListener;
import ru.shift.model.ConnectionLostListenerImpl;
import ru.shift.server.Server;
import ru.shift.server.ServerListener;
import ru.shift.server.ServerListenerImpl;

public class Main {
    public static void main(String[] args) {

        ClientListListener clientListListener = new ClientListListenerImpl();
        ClientListener clientListener = new ClientListenerImpl(clientListListener);
        ServerListener serverListener = new ServerListenerImpl();
        ConnectionLostListener connectionLostListener = new ConnectionLostListenerImpl(clientListListener);
        Server server = new Server(serverListener, clientListener, connectionLostListener);

        server.start();
    }
}