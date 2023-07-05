package com.mx.dmx.solicitudes.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteModel {

	@Schema(description = "Nombre(s) del cliente")
	@NotNull(message = "El campo nombre no puede ser nulo.")
	private String nombre;
	@Schema(description = "Apellido paterno del cliente")
	@NotNull(message = "El campo apellidoPaterno no puede ser nulo.")
	private String apellidoPaterno;
	@Schema(description = "Apellido materno del cliente")
	@NotNull(message = "El campo apellidoMaterno no puede ser nulo.")
	private String apellidoMaterno;

}
