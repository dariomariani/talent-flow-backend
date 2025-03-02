package com.dariom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse <T> {
    private T payload;
    private String message;
    private boolean success;

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, false);
    }

    public static <T> ApiResponse<T> success(T payload) {
        return new ApiResponse<>(payload, "", true);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(null, "", true);
    }
}
