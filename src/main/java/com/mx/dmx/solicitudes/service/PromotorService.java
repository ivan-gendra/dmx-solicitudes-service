package com.mx.dmx.solicitudes.service;

import java.util.List;

import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.model.entity.Promotor;

public interface PromotorService {

	public List<Promotor> getAllPromotor();

	public Promotor addNewPromotor(AgregarPromotorModel idPromotor);

}
