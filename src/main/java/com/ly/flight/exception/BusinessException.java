package com.ly.flight.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1307184728982833217L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
