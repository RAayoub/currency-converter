package com.ayoub.currencyconvertor.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper(new JsonFactory().enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES))
            .registerModule(new JavaTimeModule());

    @SneakyThrows
    public static String toJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }

}
