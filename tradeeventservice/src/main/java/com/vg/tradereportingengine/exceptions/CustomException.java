package com.vg.tradereportingengine.exceptions;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException() {

	}

	public CustomException(String message) {
		super(message);
	}
}
