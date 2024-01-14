package com.syphan.bringglobal.util.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.logging.Logger;

public class JsonConverter {

    private static final Logger logger = Logger.getLogger(JsonConverter.class.getName());

    public static String objToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            return objectWriter.writeValueAsString(object);
        } catch (Exception e) {
            logger.severe("objToJson() -> Failed to convert object to json. Error: " + e.getMessage());
            return "{}";
        }
    }
}
