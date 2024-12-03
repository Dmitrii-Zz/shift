package ru.shift.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.dto.MessageDto;
import ru.shift.dto.TextDto;

import java.util.HashSet;
import java.util.Set;

public class Mapper {
    private static final Logger log = LoggerFactory.getLogger(Mapper.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String serializationToJson(MessageDto messageDto) {
        String jsonMessage = "";
        try {
            jsonMessage = objectMapper.writeValueAsString(messageDto);
            return jsonMessage;
        } catch (JsonProcessingException e) {
            log.error("A serialization error occurred: ", e);
        }

        return jsonMessage;
    }

    public static String serializationToJson(TextDto messageDto) {
        String jsonMessage = "";
        try {
            jsonMessage = objectMapper.writeValueAsString(messageDto);
            return jsonMessage;
        } catch (JsonProcessingException e) {
            log.error("A serialization error occurred: ", e);
        }

        return jsonMessage;
    }

    public static String serializationToJson(Set<String> nameClients) {
        String jsonMessage = "";
        try {
            jsonMessage = objectMapper.writeValueAsString(nameClients);
            return jsonMessage;
        } catch (JsonProcessingException e) {
            log.error("A serialization error occurred: ", e);
        }

        return jsonMessage;
    }

    public static Set<String> deserializationNameClientsList(String strNameClients) {
        Set<String> nameClients = new HashSet<>();
        try {
            nameClients = objectMapper.readValue(strNameClients, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("A serialization error occurred: ", e);
        }

        return nameClients;
    }

    public static MessageDto deserializationMessFromJson(String message) {
        MessageDto messageDto = new MessageDto();
        try {
            messageDto = objectMapper.readValue(message, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Serialization error", e);
        }

        return messageDto;
    }

    public static TextDto deserializationTextFromJson(String message) {
        TextDto textDto = new TextDto();
        try {
            textDto = objectMapper.readValue(message, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Serialization error", e);
        }

        return textDto;
    }
}