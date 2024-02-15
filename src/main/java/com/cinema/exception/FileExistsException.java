package com.cinema.exception;

public class FileExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileExistsException(String message) {
		super(message);
	}

	
}
