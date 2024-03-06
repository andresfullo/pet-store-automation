package org.pet_store.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ObjectMapperUtils {

    public static ObjectMapper prepareObjectMapper(DateTimeFormatter formatter){
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a custom LocalDateTimeDeserializer with the custom formatter
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(formatter);

        // Create a custom LocalDateTimeSerializer with the custom formatter
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);

        // Register the custom LocalDateTimeDeserializer and LocalDateTimeSerializer
        SimpleModule customModule = new SimpleModule();
        customModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        customModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(customModule);
        return objectMapper;
    }

}
