package ru.shift.model;

public interface ConnectionLostListener {
    void notifyConnectionLost(int port);
}
