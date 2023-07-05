package com.mx.dmx.solicitudes.exception;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.zaxxer.hikari.pool.HikariPool.PoolInitializationException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Generated;

@ControllerAdvice
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ApiResponses(value = { @ApiResponse(responseCode = "406", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(JsonMappingException.class)
	public final ResponseEntity<Object> handleJsonParseException(JsonMappingException e, WebRequest request) {
		return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "409", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<Object> handleCustomException(CustomException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "409", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { PersistenceException.class })
	public final ResponseEntity<Object> handleGenericPersistenceException(PersistenceException e, WebRequest request) {
		return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.CONFLICT);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "409", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = { SQLException.class })
	public final ResponseEntity<Object> handleSQLException(SQLException e, WebRequest request) {
		return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.CONFLICT);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "424", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
	@ExceptionHandler(value = { PoolInitializationException.class })
	public final ResponseEntity<Object> handlePoolInitializationException(PoolInitializationException e,
			WebRequest request) {
		return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.FAILED_DEPENDENCY);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "400", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "415", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<MediaType> mediaTypes = e.getSupportedMediaTypes();
		return new ResponseEntity<>("El tipo de parametro soportado es " + mediaTypes.toString(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "405", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		pageNotFoundLogger.warn(e.getMessage());
		return new ResponseEntity<>("El tipo de petición soportado es " + e.getMessage(),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "409", content = {
			@Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
	@Generated
	@ResponseStatus(HttpStatus.CONFLICT)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.CONFLICT);
	}

}
