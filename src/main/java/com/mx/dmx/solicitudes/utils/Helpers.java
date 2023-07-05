package com.mx.dmx.solicitudes.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mx.dmx.solicitudes.response.RestResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Helpers {

	public Helpers() {
		super();
	}

	public Timestamp getActualDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date finalDate = new Date();

		try {
			df.setTimeZone(TimeZone.getTimeZone("Etc/GMT+6"));
			finalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(df.format(date));
		} catch (Exception e) {
			log.error("Error en procesar fecha {}", e.getMessage());
		}

		return new java.sql.Timestamp(finalDate.getTime());
	}

	public ResponseEntity<Object> responseService(Object payload, boolean success, HttpStatus code, String message) {
		RestResponse response = new RestResponse();
		response.setSuccess(success);
		response.setCode(code.value());
		response.setMessage(message);
		response.setPayload(payload);
		return new ResponseEntity<>(response, code);
	}

}
