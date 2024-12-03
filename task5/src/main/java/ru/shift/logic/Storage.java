package ru.shift.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.model.Resource;

import java.util.ArrayDeque;
import java.util.Queue;

public class Storage {
    private static final Logger log = LoggerFactory.getLogger(Storage.class);
    private final Queue<Resource> resources = new ArrayDeque<>();
    private final long storageSize;

    private final Object PRODUCER_SYNC = new Object();
    private final Object CONSUMER_SYNC = new Object();

    public Storage(long storageSize) {
        this.storageSize = storageSize;
    }

    public void putResources(Resource resource, int idProducer) throws InterruptedException {
        synchronized (PRODUCER_SYNC) {
            while (resources.size() >= storageSize) {
                try {
                    log.info("Storage is full. Resource id='{}'. Producer id='{}' Waiting.",
                            resource.getId(), idProducer);

                    PRODUCER_SYNC.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }

            resources.add(resource);

            log.info("Producer id='{}' put resource id='{}'. Resources size='{}",
                    idProducer, resource.getId(), resources.size());

            if (resources.isEmpty()) {
                PRODUCER_SYNC.notify();
            }
        }

        if (!resources.isEmpty()) {
            synchronized (CONSUMER_SYNC) {
                CONSUMER_SYNC.notify();
            }
        }
    }

    public void takeResources(int idConsumer) throws InterruptedException {
        synchronized (CONSUMER_SYNC) {
            while (resources.isEmpty()) {
                try {
                    log.info("Storage is empty. Consumer id='{}' waiting.", idConsumer);
                    CONSUMER_SYNC.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }

            Resource resource = resources.remove();
            log.info("Consumer id='{}' take resource id='{}'. Storage size='{}'",
                    idConsumer, resource.getId(), resources.size());

            if (!resources.isEmpty()) {
                CONSUMER_SYNC.notify();
            }
        }

        if (resources.isEmpty()) {
            synchronized (PRODUCER_SYNC) {
                PRODUCER_SYNC.notify();
            }
        }
    }
}