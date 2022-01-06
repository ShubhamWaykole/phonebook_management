package com.phonebook_management.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public NameAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public NameAlreadyExistsException() {
	}

}