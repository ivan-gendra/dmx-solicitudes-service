package com.mx.dmx.solicitudes.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorDetails {

	private String idError;
	private String message;
	private String level;
	private String details;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Mexico_City")
	private Date timestamp;

	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String idError, String message, String level, String details) {
		super();

		this.timestamp = new Date();
		this.message = message;
		this.details = details;
		this.idError = idError;
		this.level = level;

	}

}