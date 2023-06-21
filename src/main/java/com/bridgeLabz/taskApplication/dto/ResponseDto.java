package com.bridgeLabz.taskApplication.dto;

import lombok.Data;

@Data
public class ResponseDto {
    String message;
    Object data;

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
