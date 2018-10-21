package com.fsd.assignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

	@ExceptionHandler(BookMgmtException.class)
	public @ResponseBody ResponseEntity<ExceptionResponse> handleBookMgmtException(final BookMgmtException exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse(exception.getMessage());
		return new ResponseEntity<>(error, exception.getStatus());
	}

}
