package com.mx.dmx.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.dmx.solicitudes.model.entity.Clientes;


@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Integer> {

}
