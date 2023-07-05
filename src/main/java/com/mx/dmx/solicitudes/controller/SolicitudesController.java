package com.mx.dmx.solicitudes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.exception.ErrorDetails;
import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.service.SolicitudesService;
import com.mx.dmx.solicitudes.utils.Helpers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/solicitud")
@Slf4j
public class SolicitudesController {

	@Autowired
	SolicitudesService solicitudesService;

	@Autowired
	Helpers helpers;

	private static final String MENSAJE_APLICAR = "Registro exitoso.";
	private static final String MENSAJE_CONFLICTO_EN_SERVICIO = "Error en el registro.";

	@Operation(summary = "Servicio servicio para registrar una solicitud")
	@ApiResponse(responseCode = "200", description = "Servicio servicio para registrar una solicitud", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RegistroModel.class)) })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrarSolicitud(@Valid @RequestBody RegistroModel registrarModel) {
		try {
			var resp = solicitudesService.agregarSolicitud(registrarModel);
			return helpers.responseService(resp, true, HttpStatus.OK, MENSAJE_APLICAR);
		} catch (CustomException e) {
			var ed = new ErrorDetails("P001", e.getMessage(), e.getLevel(), e.getMessage());
			log.info("Error {}", ed);
			return helpers.responseService(ed, false, HttpStatus.CONFLICT, MENSAJE_CONFLICTO_EN_SERVICIO);
		}
	}

	@Operation(summary = "Buscar registro por id de solicitud")
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSolicitud(@RequestParam(name = "idSolicitud") String idSolicitud) {
		try {
			var resp = solicitudesService.buscarSolicitud(idSolicitud);
			return helpers.responseService(resp, true, HttpStatus.OK, MENSAJE_APLICAR);
		} catch (CustomException e) {
			var ed = new ErrorDetails("P001", e.getMessage(), e.getLevel(), e.getMessage());
			log.info("Error {}", ed);
			return helpers.responseService(ed, false, HttpStatus.CONFLICT, MENSAJE_CONFLICTO_EN_SERVICIO);
		}
	}

	@Operation(summary = "Servicio servicio para actualizar estatus de una solicitud")
	@ApiResponse(responseCode = "200", description = "Servicio servicio para actualizar estatus de una solicitud", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ActualizarSolicitudModel.class)) })
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizarSolicitud(
			@Valid @RequestBody ActualizarSolicitudModel actualizarSolicitudModel) {
		try {
			var resp = solicitudesService.actualizarSolicitud(actualizarSolicitudModel);
			return helpers.responseService(resp, true, HttpStatus.OK, MENSAJE_APLICAR);
		} catch (CustomException e) {
			var ed = new ErrorDetails("P001", e.getMessage(), e.getLevel(), e.getMessage());
			log.info("Error {}", ed);
			return helpers.responseService(ed, false, HttpStatus.CONFLICT, MENSAJE_CONFLICTO_EN_SERVICIO);
		}
	}

}
