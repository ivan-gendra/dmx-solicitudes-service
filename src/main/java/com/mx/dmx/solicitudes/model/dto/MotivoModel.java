package com.mx.dmx.solicitudes.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MotivoModel {

	@Schema(description = "Código del motivo que se pretende actualizar")
	@NotNull(message = "El campo codigo no puede ser nulo.")
	private Integer codigo;
	@Schema(description = "Motivo que se pretende actualizar")
	@NotNull(message = "El motivo codigo no puede ser nulo.")
	private String motivo;

}
