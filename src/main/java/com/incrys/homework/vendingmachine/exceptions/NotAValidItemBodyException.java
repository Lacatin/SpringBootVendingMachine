package com.incrys.homework.vendingmachine.exceptions;

public class NotAValidItemBodyException extends RuntimeException{

    public NotAValidItemBodyException() {
    }

    public NotAValidItemBodyException(String message) {
        super(message);
    }

    public NotAValidItemBodyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAValidItemBodyException(Throwable cause) {
        super(cause);
    }

    public NotAValidItemBodyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
