package ru.shift.client;

import ru.shift.view.MainWindow;

public class ServerListenerImpl implements ServerListener {
    private final MainWindow view;
    private final Client client;

    public ServerListenerImpl(MainWindow view, Client client) {
        this.view = view;
        this.client = client;
    }

    @Override
    public void accessToServerLost() {
        view.setInformationServer("Соединение с сервером потеряно. Нажмите здесь, чтобы установить новое соединение");
        client.setStatusClient(StatusClient.NOT_CONNECTED);
    }
}
