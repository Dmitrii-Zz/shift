package ru.shift.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TextDto;
import ru.shift.utils.Mapper;
import ru.shift.view.MainWindow;
import ru.shift.view.NameWindow;
import ru.shift.view.NameWindowListener;

import java.net.InetAddress;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ClientListenerImpl implements ClientListener {
    private static final Logger log = LoggerFactory.getLogger(ClientListenerImpl.class);
    private final MainWindow view;
    private final NameWindowListener nameWindowListener;
    private final Client client;

    public ClientListenerImpl(MainWindow view, NameWindowListener nameWindowListener, Client client) {
        this.view = view;
        this.nameWindowListener = nameWindowListener;
        this.client = client;
    }

    @Override
    public void handleMessage(String message) {
        processMessage(Mapper.deserializationMessFromJson(message));
    }

    private void processMessage(MessageDto messageDto) {
        switch (messageDto.getTypeMessage()) {
            case REQUEST_NAME -> sendName();
            case WRONG_NAME -> wrongName();
            case REGISTERED -> registeredOnServer();
            case NAME_CLIENTS -> clientList(messageDto);
            case MESSAGE -> visibleMess(messageDto);
            case INFO_MESSAGE -> visibleInfoMessage(messageDto);
            default -> System.out.println("Не поддерживается");
        }
    }

    private void sendName() {
        client.setStatusClient(StatusClient.NEED_NAME);
        String message = "Соединение с сервером установлено. Нажмите здесь, чтобы ввести имя на севрере";
        view.setInformationServer(message);
        new NameWindow(view, nameWindowListener).setVisible(true);
    }

    private void wrongName() {
        String message = "Имя '" + client.getName() + "' занято на сервере. Нажмите здесь, чтобы ввести другое имя";
        view.setInformationServer(message);
        client.setStatusClient(StatusClient.NEED_NAME);
    }

    private void registeredOnServer() {
        Socket socket = client.getSocket();
        InetAddress address = socket.getInetAddress();
        String text = "Подключены к серверу " + address.getHostName() + ":" + socket.getPort();
        view.setInformationServer(text);
        client.setStatusClient(StatusClient.CONNECTED);
    }

    private void visibleMess(MessageDto messageDto) {
        TextDto textDto = Mapper.deserializationTextFromJson(messageDto.getMessage());
        view.setNewMessage(formatMessage(textDto));
    }

    private String formatMessage(TextDto textDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        String localDate = textDto.getDateTime().format(formatter);
        return "[" + localDate + "] " + textDto.getUserName() + ": " + textDto.getMessage();
    }

    private void clientList(MessageDto messageDto) {
        Set<String> nameClients = Mapper.deserializationNameClientsList(messageDto.getMessage());
        StringBuilder names = new StringBuilder();
        for (String name : nameClients) {
            names.append(name);
            names.append(System.lineSeparator());
        }

        view.setClientList(names.toString());
    }

    private void visibleInfoMessage(MessageDto messageDto) {
        view.setNewMessage(messageDto.getMessage());
    }
}