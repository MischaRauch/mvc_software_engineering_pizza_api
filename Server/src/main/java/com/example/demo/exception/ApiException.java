package main.java.com.example.demo.exception;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
    private final String errorCode;
    private final ZonedDateTime timestamp;

    public ApiException(String message, String errorCode, ZonedDateTime timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
