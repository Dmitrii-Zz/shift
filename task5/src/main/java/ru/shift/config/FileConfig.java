package ru.shift.config;

public enum FileConfig {
    PATH_FILE_CONFIG("app.properties");

    private final String pathFIleConfig;

    FileConfig(String pathFIleConfig) {
        this.pathFIleConfig = pathFIleConfig;
    }

    public String getPathFIleConfig() {
        return pathFIleConfig;
    }
}