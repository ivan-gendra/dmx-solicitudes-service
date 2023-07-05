package com.mx.dmx.solicitudes.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DispersionModel {

	private String idSolicitud;
	private Integer idCredito;
	private Double capitalDispersado;
	private Double monto;
	private Double tasa;
	private Double plazo;
	private String frecuencia;

}
