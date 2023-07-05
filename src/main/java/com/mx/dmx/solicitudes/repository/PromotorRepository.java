package com.mx.dmx.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.dmx.solicitudes.model.entity.Promotor;

@Repository
public interface PromotorRepository extends CrudRepository<Promotor, Integer> {

	Promotor findByIdPromotor(String idPromotor);

}
