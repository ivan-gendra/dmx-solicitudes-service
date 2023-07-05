package com.mx.dmx.solicitudes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;
	private final HttpStatus status;
	private final String level;

	public CustomException(String message, HttpStatus status, String level) {
		this.message = message;
		this.status = status;
		this.level = level;

	}
}