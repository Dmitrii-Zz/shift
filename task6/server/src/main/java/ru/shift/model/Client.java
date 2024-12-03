package ru.shift.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    private final Socket socket;

    private final ClientListener clientListener;
    private final ConnectionLostListener connectionLostListener;

    private BufferedReader in;
    private BufferedWriter out;

    private String name;

    public Client(Socket socket, ClientListener clientListener, ConnectionLostListener connectionLostListener) {
        this.socket = socket;
        this.clientListener = clientListener;
        this.connectionLostListener = connectionLostListener;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            log.error("Error creating resources: ", e);
        }

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
                            clientListener.handleMessage(word, socket.getPort());
                        }
                    }
                } catch (IOException e) {
                    close();
                    log.info("Port '{}' is disconnecting", socket.getPort());
                    log.error("error", e);
                    connectionLostListener.notifyConnectionLost(socket.getPort());
                    return;
                }
            }
        }).start();
    }

    public void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException e) {
            log.error("an error occurred while writing to the stream.", e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            log.error("Error closing resources", e);
        }
    }
}