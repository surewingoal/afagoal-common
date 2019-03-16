package com.afagoal.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by BaoCai on 2019/3/16. Description:
 */
public class CustomDateTimeDeserialize extends JsonDeserializer<LocalDateTime> {
    public CustomDateTimeDeserialize() {
    }

    private static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);

    public static LocalDateTime parseToLocalDateTime(String string) {
        return LocalDateTime.parse(string, localDateTimeFormatter);
    }

    public LocalDateTime deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            return parseToLocalDateTime(p.getText().trim());
        } catch (Exception var4) {
            return null;
        }
    }


}
