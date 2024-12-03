package ru.cft.javaLessons.miner.config;

import java.util.Properties;

public class Config {
    private final Properties properties = PropertyLoader.loadProperties("app.properties");

    public String getPathFileRecord() {
        return properties.getProperty("fileRecords");
    }
}