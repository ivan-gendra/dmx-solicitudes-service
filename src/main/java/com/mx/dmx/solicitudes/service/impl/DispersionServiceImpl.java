package com.mx.dmx.solicitudes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.DispersionModel;
import com.mx.dmx.solicitudes.model.entity.Dispersion;
import com.mx.dmx.solicitudes.model.entity.Estatus;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;
import com.mx.dmx.solicitudes.repository.DispersionRepository;
import com.mx.dmx.solicitudes.repository.EstatusRepository;
import com.mx.dmx.solicitudes.repository.SolicitudesRepository;
import com.mx.dmx.solicitudes.service.DispersionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DispersionServiceImpl implements DispersionService {

	@Autowired
	DispersionRepository dispersionRepository;

	@Autowired
	SolicitudesRepository solicitudesRepository;

	@Autowired
	EstatusRepository estatusRepository;

	@Value("${code.warning}")
	String warning;

	@Override
	public Dispersion dispersarCredito(DispersionModel dispersionModel) {
		log.info("Comienza el proceso de dispersión {}", dispersionModel);
		Solicitudes solicitud = solicitudesRepository.findByIdSolicitud(dispersionModel.getIdSolicitud());

		if (solicitud != null) {

			Estatus estatus = estatusRepository
					.findTopByIdSolicitudOrderByIdEstatusDesc(dispersionModel.getIdSolicitud());

			if (estatus != null && estatus.getEstatus().equals("Aprobado")) {

				Dispersion dispersion = new Dispersion();
				dispersion.setCapitalDispersado(dispersionModel.getCapitalDispersado());
				dispersion.setFrecuencia(dispersionModel.getFrecuencia());
				dispersion.setIdCrredito(dispersionModel.getIdCredito());
				dispersion.setIdSolicitud(dispersionModel.getIdSolicitud());
				dispersion.setMonto(dispersionModel.getMonto());
				dispersion.setPlazo(dispersionModel.getPlazo());
				dispersion.setTasa(dispersionModel.getTasa());

				estatus.setEstatus("Dispersado");
				estatusRepository.save(estatus);

				return dispersionRepository.save(dispersion);

			} else {
				throw new CustomException("El credito no esta aprobado", HttpStatus.CONFLICT, warning);
			}

		} else {
			throw new CustomException("No existe la solicitud que se quiere dispersar", HttpStatus.CONFLICT, warning);
		}
	}

}
