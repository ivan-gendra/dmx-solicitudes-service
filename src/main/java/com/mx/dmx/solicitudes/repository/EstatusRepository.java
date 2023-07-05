package com.mx.dmx.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.dmx.solicitudes.model.entity.Estatus;

@Repository
public interface EstatusRepository extends CrudRepository<Estatus, Integer> {

	Estatus findTopByIdSolicitudOrderByIdEstatusDesc(String idEstatus);

}
