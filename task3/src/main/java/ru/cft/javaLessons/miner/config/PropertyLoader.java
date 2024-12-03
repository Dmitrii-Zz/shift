package ru.cft.javaLessons.miner.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(PropertyLoader.class);

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (var inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("resource not found: " + fileName);
            }
        } catch (IOException e) {
            log.error("file properties not found. Completion of the program");
            System.exit(0);
        }

        return properties;
    }
}