package com.afagoal.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by BaoCai on 2019/3/16. Description:
 */
public class CustomDateDeserialize extends JsonDeserializer<LocalDate> {
    public CustomDateDeserialize() {
    }

    public LocalDate deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            return LocalDate.parse(p.getText().trim(), CustomDateSerialize.PATTERN);
        } catch (Exception var4) {
            return null;
        }
    }
}
