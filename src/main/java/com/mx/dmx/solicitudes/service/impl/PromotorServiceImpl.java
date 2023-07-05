package com.mx.dmx.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.model.entity.Promotor;
import com.mx.dmx.solicitudes.repository.PromotorRepository;
import com.mx.dmx.solicitudes.service.PromotorService;
import com.mx.dmx.solicitudes.utils.Helpers;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotorServiceImpl implements PromotorService {

	@Autowired
	PromotorRepository promotorRepository;

	@Autowired
	Helpers helpers;

	@Override
	public List<Promotor> getAllPromotor() {
		return (List<Promotor>) promotorRepository.findAll();
	}

	@Override
	public Promotor addNewPromotor(AgregarPromotorModel agregarPromotorModel) {
		log.info("Comienza el proceso de registr para el promotor {}", agregarPromotorModel);

		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno(agregarPromotorModel.getApellidMaterno());
		promotor.setApellidoPaterno(agregarPromotorModel.getApellidoPaterno());
		promotor.setCreatedAt(helpers.getActualDate());
		promotor.setEmail(agregarPromotorModel.getEmail());
		promotor.setIdPromotor(agregarPromotorModel.getIdPromotor());
		promotor.setNombre(agregarPromotorModel.getNombre());
		return promotorRepository.save(promotor);
	}

}
