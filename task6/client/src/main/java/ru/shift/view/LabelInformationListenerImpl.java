package ru.shift.view;

import ru.shift.client.Client;

public class LabelInformationListenerImpl implements LabelInformationListener {
    private final MainWindow view;
    private final Client client;
    private final NameWindowListener nameWindowListener;

    public LabelInformationListenerImpl(MainWindow view, Client client, NameWindowListener nameWindowListener) {
        this.view = view;
        this.client = client;
        this.nameWindowListener = nameWindowListener;
    }

    @Override
    public void clickLabel() {
        switch (client.getStatusClient()) {
            case NOT_CONNECTED -> connectionServer();
            case NEED_NAME -> sendName();
        }
    }

    public void connectionServer() {
        ConnectorServerListener connectorServerListener = new ConnectorServerListenerImpl(client, view);
        new ConnectorServer(view, connectorServerListener);
    }

    public void sendName() {
        new NameWindow(view, nameWindowListener).setVisible(true);
    }
}