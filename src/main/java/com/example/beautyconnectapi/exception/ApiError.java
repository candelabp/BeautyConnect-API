package com.example.beautyconnectapi.exception;


import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;

public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public static ApiError of(int status, String error, String message, HttpServletRequest request) {
        return new ApiError(
                Instant.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );
    }
}
