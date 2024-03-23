package com.example.backend.handler;

import lombok.Getter;

import static com.example.backend.constants.HttpStatus.HTTP_STATUS_200;
import static com.example.backend.constants.HttpStatus.HTTP_STATUS_500;


@Getter
public enum HttpMessage {
    SUCCESS(HTTP_STATUS_200, "success"),
    SYSTEM_ERROR(HTTP_STATUS_500, "system error");
    final Integer code;
    final String message;

    HttpMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
