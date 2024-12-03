package ru.shift.client;

public class WriterListenerImpl implements WriterListener {
    private final Client client;

    public WriterListenerImpl(Client client) {
        this.client = client;
    }

    @Override
    public void send(String response) {
        client.send(response);
    }
}
