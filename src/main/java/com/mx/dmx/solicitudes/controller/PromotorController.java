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
import org.springframework.web.bind.annotation.RestController;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.exception.ErrorDetails;
import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.service.PromotorService;
import com.mx.dmx.solicitudes.utils.Helpers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/promotor")
@Slf4j
public class PromotorController {

	@Autowired
	PromotorService promotorService;

	@Autowired
	Helpers helpers;

	private static final String MESAJE_VALIDA = "Consulta exitosa.";
	private static final String MENSAJE_APLICAR = "Registro exitoso.";
	private static final String MENSAJE_CONFLICTO_EN_SERVICIO = "Error en el registro.";

	/***
	 * Controlador de consulta de promotores
	 * 
	 * @return
	 */
	@Operation(summary = "Solicitar promotores en BD")
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPromotor() {
		var resp = promotorService.getAllPromotor();
		return helpers.responseService(resp, true, HttpStatus.OK, MESAJE_VALIDA);
	}

	/***
	 * Controlador para agregar promotoresnuevos
	 * 
	 * @param agregarPromotorModel
	 * @return
	 */
	@Operation(summary = "Servicio servicio para registrar promotores a BD")
	@ApiResponse(responseCode = "200", description = "Servicio servicio para registrar promotores a BD", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = AgregarPromotorModel.class)) })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> agregarPromotor(@Valid @RequestBody AgregarPromotorModel agregarPromotorModel) {
		try {
			var resp = promotorService.addNewPromotor(agregarPromotorModel);
			return helpers.responseService(resp, true, HttpStatus.OK, MENSAJE_APLICAR);
		} catch (CustomException e) {
			var ed = new ErrorDetails("P001", e.getMessage(), e.getLevel(), e.getMessage());
			log.info("Error {}", ed);
			return helpers.responseService(ed, false, HttpStatus.CONFLICT, MENSAJE_CONFLICTO_EN_SERVICIO);
		}
	}

}
