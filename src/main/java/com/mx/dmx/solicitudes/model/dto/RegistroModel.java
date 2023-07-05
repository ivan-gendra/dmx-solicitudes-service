package com.mx.dmx.solicitudes.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistroModel {

	@Schema(description = "Id del promotor")
	@NotNull(message = "El campo promotor no puede ser nulo.")
	private String promotor;
	@Schema(description = "Empresa a la que se integra el cliente")
	@NotNull(message = "El campo empresa no puede ser nulo.")
	private String empresa;
	private ClienteModel cliente;
	private SolicitudModel solicitud;

}
