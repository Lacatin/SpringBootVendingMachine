package com.incrys.homework.vendingmachine.exceptions;

public class NotAnExistingItemException extends RuntimeException {

	public NotAnExistingItemException() {
	}

	private static final long serialVersionUID = 1L;

	public NotAnExistingItemException(String message) {
		super(message);
	}

	public NotAnExistingItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAnExistingItemException(Throwable cause) {
		super(cause);
	}

	public NotAnExistingItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
