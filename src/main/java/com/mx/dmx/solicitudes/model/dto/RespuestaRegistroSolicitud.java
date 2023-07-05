package com.mx.dmx.solicitudes.model.dto;

import com.mx.dmx.solicitudes.model.entity.Clientes;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaRegistroSolicitud {

	private Clientes cliente;
	private Solicitudes solicitud;

}
