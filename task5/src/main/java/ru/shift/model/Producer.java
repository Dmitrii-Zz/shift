package ru.shift.model;

import ru.shift.logic.FactoryResource;
import ru.shift.logic.Storage;

public class Producer extends Thread {
    private final int id;
    private final long producerTimeMillis;
    private final Storage storage;

    public Producer(int id, long producerTimeMillis, Storage storage) {
        this.id = id;
        this.producerTimeMillis = producerTimeMillis;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(producerTimeMillis);
                storage.putResources(FactoryResource.createResource(), id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
