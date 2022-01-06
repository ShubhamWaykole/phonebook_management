package com.phonebook_management.exceptions;

public class NumberAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public NumberAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public NumberAlreadyExistsException() {
	}

}