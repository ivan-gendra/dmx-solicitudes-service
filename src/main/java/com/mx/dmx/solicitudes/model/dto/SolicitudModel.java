package com.mx.dmx.solicitudes.model.dto;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolicitudModel {

	@Schema(description = "Id que tendrá la solicitud")
	@NotNull(message = "El campo idSolicitud no puede ser nulo.")
	private String idSolicitud;

	@Schema(description = "Monto del registro")
	@NotNull(message = "El campo monto no puede ser nulo.")
	private Double monto;

	@Schema(description = "Nombre del producto")
	@NotNull(message = "El campo producto no puede ser nulo.")
	private String producto;

	@Schema(description = "Tipo de la solicitud")
	@NotNull(message = "El campo tipoSolicitud no puede ser nulo.")
	private String tipoSolicitud;

	@Schema(description = "Id del tipo de la solicitud")
	@NotNull(message = "El campo idTipoSolicitud no puede ser nulo.")
	private String idTipoSolicitud;

	@Schema(description = "Tasa a la que corresponde el registro")
	@NotNull(message = "El campo taza no puede ser nulo.")
	private Double tasa;

	@Schema(description = "Plazos de pago")
	@NotNull(message = "El campo plazo no puede ser nulo.")
	private Integer plazo;

	@Schema(description = "Frecuencia con la que se realiza el pago Semanal/Quincenal/Mensual ")
	@NotNull(message = "El campo frecuencia no puede ser nulo.")
	private String frecuencia;

	@Schema(description = "Fecha de la solicitud")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "El campo fechaSolicitud no puede ser nulo.")
	private Date fechaSolicitud;

}
