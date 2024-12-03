package ru.cft.javaLessons.miner.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.config.Config;
import ru.cft.javaLessons.miner.model.Record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final Logger log = LoggerFactory.getLogger(FileManager.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final Config config = new Config();

    public List<Record> readFileRecord() {
        File file = new File(config.getPathFileRecord());

        List<Record> records = new ArrayList<>();

        try {
            records = MAPPER.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("An error occurred while reading the file: ", e);
        }

        return records;
    }

    public void writeRecords(List<Record> records) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(config.getPathFileRecord()))) {
            String jsonRecords = MAPPER.writeValueAsString(records);
            pw.print(jsonRecords);
        } catch (JsonProcessingException e) {
            log.error("serialization error: ", e);
        } catch (IOException e) {
            log.info("An error occurred while writing the file: ", e);
        }
    }
}