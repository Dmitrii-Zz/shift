package ru.shift.config;

public class Config {
    private final PropertyLoader properties = new PropertyLoader();

    public int getCountProducer(int valueDefault) {
        return properties.getInt(KeyProperties.COUNT_PRODUCER.name(), valueDefault);
    }

    public long getProducerTimeMillis(long valueDefault) {
        return properties.getLong(KeyProperties.PRODUCER_TIME_MILLIS.name(), valueDefault);
    }

    public int getCountConsumer(int valueDefault) {
        return properties.getInt(KeyProperties.COUNT_CONSUMER.name(), valueDefault);
    }

    public long getConsumerTimeMillis(long valueDefault) {
        return properties.getLong(KeyProperties.CONSUMER_TIME_MILLIS.name(), valueDefault);
    }

    public int getStorageSize(int valueDefault) {
        return properties.getInt(KeyProperties.STORAGE_SIZE.name(), valueDefault);
    }
}