package com.syphan.bringglobal.util.converter;

import com.syphan.bringglobal.model.dto.SimpleMessageDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConverterTest {

    @Test
    public void testConvertObjToJson() {
        SimpleMessageDto simpleMessageDto = new SimpleMessageDto("Success message");
        String expectedJson = "{\n" +
                "  \"message\" : \"Success message\"\n" +
                "}";
        assertEquals(JsonConverter.objToJson(simpleMessageDto), expectedJson);
    }
}
