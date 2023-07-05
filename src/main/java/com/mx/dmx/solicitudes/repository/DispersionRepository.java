package com.mx.dmx.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.dmx.solicitudes.model.entity.Dispersion;

@Repository
public interface DispersionRepository extends CrudRepository<Dispersion, Integer> {

}
