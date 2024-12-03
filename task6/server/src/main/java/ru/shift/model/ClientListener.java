package ru.shift.model;

public interface ClientListener {
    void handleMessage(String request, int port);
}
