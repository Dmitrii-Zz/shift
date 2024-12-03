package ru.shift.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.FileNotFoundMyException;

import java.io.*;

public class FileManager {
    private static final Logger LOG = LoggerFactory.getLogger(FileManager.class);
    private static final String SEPARATOR = " ";

    public String[] readFile(String pathFile) {
        File file = new File(pathFile);
        String[] content = new String[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {

            content[0] = reader.readLine();
            content[1] = reader.readLine();

        } catch (IOException e) {
            LOG.error("При чтении файла произошла ошибка: ", e);
            throw new FileNotFoundMyException("Указанный путь или файл не существует: " + pathFile);
        }

        return content;
    }
}
