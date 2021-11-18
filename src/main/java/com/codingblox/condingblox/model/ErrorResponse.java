package com.codingblox.condingblox.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse(String message) {
        this.code = (int) ( 100 * Math.random());
        this.message = message;
    }
}
