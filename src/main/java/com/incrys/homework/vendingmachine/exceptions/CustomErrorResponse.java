package com.incrys.homework.vendingmachine.exceptions;

import java.time.LocalDateTime;
import java.util.Date;

public class CustomErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
