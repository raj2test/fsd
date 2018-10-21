package com.fsd.assignment.exception;

import org.springframework.http.HttpStatus;

public class BookMgmtException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus status;

	public BookMgmtException(String message) {
		super(message);
		this.message = message;
		this.status = HttpStatus.BAD_REQUEST;

	}

	public BookMgmtException(String message, HttpStatus status) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
