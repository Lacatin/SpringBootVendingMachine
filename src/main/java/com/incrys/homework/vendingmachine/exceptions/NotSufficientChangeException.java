package com.incrys.homework.vendingmachine.exceptions;

public class NotSufficientChangeException extends RuntimeException {

	public NotSufficientChangeException() {
	}

	private static final long serialVersionUID = 1L;

	public NotSufficientChangeException(String message) {
		super(message);
	}

	public NotSufficientChangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSufficientChangeException(Throwable cause) {
		super(cause);
	}

	public NotSufficientChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
