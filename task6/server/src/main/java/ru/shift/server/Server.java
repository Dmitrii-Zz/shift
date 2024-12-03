package ru.shift.server;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.model.Client;
import ru.shift.model.ClientListener;
import ru.shift.model.ConnectionLostListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static final ConcurrentHashMap<Integer, Client> unregisteredClients = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Integer, Client> registeredClients = new ConcurrentHashMap<>();

    private final Config config = ConfigFactory.load();

    private final ServerListener serverListener;
    private final ClientListener clientListener;
    private final ConnectionLostListener connectionLostListener;

    public Server(ServerListener serverListener, ClientListener clientListener, ConnectionLostListener connectionLostListener) {
        this.serverListener = serverListener;
        this.clientListener = clientListener;
        this.connectionLostListener = connectionLostListener;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(config.getInt("server.port"))) {
            while (true) {
                Socket socket = serverSocket.accept();
                log.info("Client {} connected", socket.getPort());

                Client client = new Client(socket, clientListener, connectionLostListener);
                unregisteredClients.put(socket.getPort(), client);

                serverListener.connectionClient(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}