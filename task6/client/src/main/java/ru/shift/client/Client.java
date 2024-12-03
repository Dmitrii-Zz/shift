package ru.shift.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements Closeable {
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    private String name;
    private StatusClient statusClient = StatusClient.NOT_CONNECTED;
    private ClientListener clientListener;
    private ServerListener serverListener;

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public void connectionServer(String host, int port) throws IOException {
        log.info("host '{}' and port '{}' entered", host, port);
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        read();
    }

    private void read() {
        new Thread(() -> {
            String word;
            while (true) {
                try {
                    if ((word = in.readLine()) == null) {
                        break;
                    } else {
                        if (clientListener != null) {
                            clientListener.handleMessage(word);
                        }
                    }
                } catch (IOException e) {
                    serverListener.accessToServerLost();
                    log.info("Access to server lost", e);
                    return;
                }
            }
        }).start();
    }

    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    public void setServerListener(ServerListener serverListener) {
        this.serverListener = serverListener;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public StatusClient getStatusClient() {
        return statusClient;
    }

    public void setStatusClient(StatusClient statusClient) {
        this.statusClient = statusClient;
    }

    public void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException e) {
            log.error("an error occurred while writing to the stream.", e);
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}