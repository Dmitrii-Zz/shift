package ru.shift.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.client.Client;
import ru.shift.client.StatusClient;

import java.io.IOException;

public class ConnectorServerListenerImpl implements ConnectorServerListener {
    private static final Logger log = LoggerFactory.getLogger(ConnectorServerListenerImpl.class);
    private final Client client;
    private final MainWindow view;

    public ConnectorServerListenerImpl(Client client, MainWindow view) {
        this.client = client;
        this.view = view;
    }

    @Override
    public void connect(String host, String strPort) {
        try {
            int port = Integer.parseInt(strPort);
            client.connectionServer(host, port);
        } catch (IOException | NumberFormatException e) {
            client.setStatusClient(StatusClient.NOT_CONNECTED);
            String message = "Ошибка соединения с сервером. Нажмите здесь, чтобы установить новое соединеие";
            view.setInformationServer(message);
            log.info("error", e);
        }
    }
}