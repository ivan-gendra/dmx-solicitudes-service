package com.mx.dmx.solicitudes.service;

import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.model.dto.RespuestaActualizar;
import com.mx.dmx.solicitudes.model.dto.RespuestaRegistroSolicitud;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;

public interface SolicitudesService {

	public RespuestaRegistroSolicitud agregarSolicitud(RegistroModel registroModel);

	public Solicitudes buscarSolicitud(String idSolicitud);

	public RespuestaActualizar actualizarSolicitud(ActualizarSolicitudModel actualizarSolicitudModel);

}
