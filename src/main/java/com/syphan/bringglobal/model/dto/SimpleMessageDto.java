package com.syphan.bringglobal.model.dto;

public class SimpleMessageDto {

    private String message;

    public SimpleMessageDto() {
    }

    public SimpleMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SimpleMessageDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
