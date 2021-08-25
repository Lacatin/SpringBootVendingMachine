package com.incrys.homework.vendingmachine.exceptions;

public class InvalidCoinTypeException extends RuntimeException{

	public InvalidCoinTypeException() {
	}

	public InvalidCoinTypeException(String message) {
		super(message);
	}

	public InvalidCoinTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCoinTypeException(Throwable cause) {
		super(cause);
	}

	public InvalidCoinTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
