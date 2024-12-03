package ru.shift.server;

import java.net.Socket;

public interface ServerListener {
    void connectionClient(Socket socket);
}
