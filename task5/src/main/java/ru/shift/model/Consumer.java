package ru.shift.model;

import ru.shift.logic.Storage;

public class Consumer extends Thread {
    private final int id;
    private final long consumerTimeMillis;
    private final Storage storage;

    public Consumer(int id, long consumerTimeMillis, Storage storage) {
        this.id = id;
        this.consumerTimeMillis = consumerTimeMillis;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(consumerTimeMillis);
                storage.takeResources(id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}