package ru.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.client.Client;
import ru.shift.client.ClientListener;
import ru.shift.client.ClientListenerImpl;
import ru.shift.client.ServerListener;
import ru.shift.client.ServerListenerImpl;
import ru.shift.client.WriterListener;
import ru.shift.client.WriterListenerImpl;
import ru.shift.controller.TextFieldController;
import ru.shift.view.ConnectorServer;
import ru.shift.view.ConnectorServerListener;
import ru.shift.view.ConnectorServerListenerImpl;
import ru.shift.view.LabelInformationListener;
import ru.shift.view.LabelInformationListenerImpl;
import ru.shift.view.MainWindow;
import ru.shift.view.NameWindowListener;
import ru.shift.view.NameWindowListenerImpl;
import ru.shift.view.TextFieldListener;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);

        MainWindow mainWindow = new MainWindow();
        mainWindow.create();

        try (Client client = new Client()) {
            WriterListener writerListener = new WriterListenerImpl(client);
            NameWindowListener nameWindowListener = new NameWindowListenerImpl(writerListener, client);
            ClientListener clientListener = new ClientListenerImpl(mainWindow, nameWindowListener, client);
            client.setClientListener(clientListener);
            ServerListener serverListener = new ServerListenerImpl(mainWindow, client);
            client.setServerListener(serverListener);
            ConnectorServerListener connectorServerListener = new ConnectorServerListenerImpl(client, mainWindow);
            new ConnectorServer(mainWindow, connectorServerListener);

            LabelInformationListener labelInformationListener =
                    new LabelInformationListenerImpl(mainWindow, client, nameWindowListener);
            mainWindow.setLabelInformationListener(labelInformationListener);

            TextFieldListener textFieldListener = new TextFieldController(client);
            mainWindow.setTextFieldListener(textFieldListener);
            while (true) {
            }
        } catch (IOException e) {
            log.info("Error occurred", e);
        }
    }
}