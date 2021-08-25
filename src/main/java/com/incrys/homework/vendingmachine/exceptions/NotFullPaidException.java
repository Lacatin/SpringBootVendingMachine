package com.incrys.homework.vendingmachine.exceptions;

public class NotFullPaidException extends RuntimeException {

	public NotFullPaidException() {
	}

	private static final long serialVersionUID = 1L;

	public NotFullPaidException(String message) {
		super(message);
	}

	public NotFullPaidException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFullPaidException(Throwable cause) {
		super(cause);
	}

	public NotFullPaidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
