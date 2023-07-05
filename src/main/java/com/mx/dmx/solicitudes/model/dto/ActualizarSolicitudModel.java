package com.mx.dmx.solicitudes.model.dto;

import java.sql.Date;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActualizarSolicitudModel {

	@Schema(description = "Id de la solicitud que se pretende actualizar")
	@NotNull(message = "El campo idSolicitud no puede ser nulo.")
	private String idSolicitud;
	@Schema(description = "Estatus al que se actualizara el registro Aprobado/Rechazado")
	@NotNull(message = "El campo estatus no puede ser nulo.")
	private String estatus;
	private ArrayList<MotivoModel> motivo = new ArrayList<>();
	@Schema(description = "Fecha en la que se realiza la actualización")
	@NotNull(message = "El campo fechaCambio no puede ser nulo.")
	private Date fechaCambio;

}
