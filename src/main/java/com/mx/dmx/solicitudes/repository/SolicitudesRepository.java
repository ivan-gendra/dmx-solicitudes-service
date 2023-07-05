package com.mx.dmx.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.dmx.solicitudes.model.entity.Solicitudes;

@Repository
public interface SolicitudesRepository extends CrudRepository<Solicitudes, Integer> {

	Solicitudes findByIdSolicitud(String idSolicitud);

}
