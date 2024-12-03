package ru.shift.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(PropertyLoader.class);
    private final Properties properties = new Properties();

    public PropertyLoader() {
        loadProperties();
    }

    public void loadProperties() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String pathFileConfig = FileConfig.PATH_FILE_CONFIG.getPathFIleConfig();

        try (var inputStream = classLoader.getResourceAsStream(pathFileConfig)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("resource not found: " + pathFileConfig);
            }

        } catch (IOException e) {
            log.error("file properties not found");
        }
    }

    public int getInt(String key, int valueDefault) {
        int value = valueDefault;
        try {
            value = Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException e) {
            log.error("Error in properties key: '{}'", key);
        }

        return value;
    }

    public long getLong(String key, long valueDefault) {
        long value = valueDefault;
        try {
            value = Long.parseLong(properties.getProperty(key));
        } catch (NumberFormatException e) {
            log.error("Error in properties key: '{}'", key);
        }

        return value;
    }
}