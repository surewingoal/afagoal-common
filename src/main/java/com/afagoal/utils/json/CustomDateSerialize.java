package com.afagoal.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by BaoCai on 18/6/5.
 * Description:
 */
public class CustomDateSerialize extends JsonSerializer<LocalDate> {

    public static final String PATTERN_STR = "yyyy-MM-dd";
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern(PATTERN_STR);

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
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
