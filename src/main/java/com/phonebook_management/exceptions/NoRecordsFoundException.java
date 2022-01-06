package com.phonebook_management.exceptions;

public class NoRecordsFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public NoRecordsFoundException(String message) {
		super(message);
		this.message = message;
	}

	public NoRecordsFoundException() {
	}

}