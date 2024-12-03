package ru.shift;

import ru.shift.config.Config;
import ru.shift.logic.Storage;
import ru.shift.model.Consumer;
import ru.shift.model.Producer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        Storage storage = new Storage(config.getStorageSize(100));
        List<Thread> threads = new ArrayList<>();

        int countProducer = config.getCountProducer(4);
        for (int i = 0; i < countProducer; i++) {
            Producer producer = new Producer(i, config.getProducerTimeMillis(500), storage);
            threads.add(producer);

        }

        int countConsumer = config.getCountConsumer(2);
        for (int i = 0; i < countConsumer; i++) {
            Consumer consumer = new Consumer(i, config.getConsumerTimeMillis(100), storage);
            threads.add(consumer);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}