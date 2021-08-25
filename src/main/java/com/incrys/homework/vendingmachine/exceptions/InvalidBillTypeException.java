package com.incrys.homework.vendingmachine.exceptions;

public class InvalidBillTypeException extends RuntimeException{

    public InvalidBillTypeException() {
    }

    public InvalidBillTypeException(String message) {
        super(message);
    }

    public InvalidBillTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBillTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidBillTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
