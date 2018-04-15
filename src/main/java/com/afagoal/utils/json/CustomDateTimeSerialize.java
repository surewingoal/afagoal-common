package com.afagoal.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeSerialize extends JsonSerializer<LocalDateTime> {
    public static final String PATTERN_STR = "yyyy-MM-dd hh:mm:ss";
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern(PATTERN_STR);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        String formatValue;
        if (value == null) {
            formatValue = null;
        }else {
            formatValue = value.format(PATTERN);
        }
        gen.writeString(formatValue);
    }
}
