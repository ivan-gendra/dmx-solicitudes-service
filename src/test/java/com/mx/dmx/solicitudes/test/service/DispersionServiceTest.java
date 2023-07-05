package com.mx.dmx.solicitudes.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.DispersionModel;
import com.mx.dmx.solicitudes.model.entity.Estatus;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;
import com.mx.dmx.solicitudes.repository.EstatusRepository;
import com.mx.dmx.solicitudes.repository.SolicitudesRepository;
import com.mx.dmx.solicitudes.service.DispersionService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
class DispersionServiceTest {

	@Autowired
	DispersionService dispersionService;

	@MockBean
	SolicitudesRepository solicitudesRepository;

	@MockBean
	EstatusRepository estatusRepository;

	private static final DispersionModel DISPERSIONMODEL = new DispersionModel();

	@BeforeAll
	static void setUp() {
		DISPERSIONMODEL.setCapitalDispersado(100.0);
		DISPERSIONMODEL.setFrecuencia("Semanal");
		DISPERSIONMODEL.setIdCredito(1);
		DISPERSIONMODEL.setIdSolicitud("1");
		DISPERSIONMODEL.setMonto(100.0);
		DISPERSIONMODEL.setPlazo(1.0);
		DISPERSIONMODEL.setTasa(10.0);
	}

	@Test
	void test_01_dispersarCredito() {
		Solicitudes solicitud = new Solicitudes();
		solicitud.setCreatedAt(new Timestamp(0));
		solicitud.setEmpresa("1");
		solicitud.setFechaSolicitud(new Date(0));
		solicitud.setFrecuencia("Semanal");
		solicitud.setIdCliente(1);
		solicitud.setIdRegistro(1);
		solicitud.setIdSolicitud("1");
		solicitud.setIdTipoSolicitud("1");
		solicitud.setMonto(200.0);
		solicitud.setPlazo(1);
		solicitud.setProducto("Test");
		solicitud.setPromotor("1");
		solicitud.setTasa(10.0);
		solicitud.setTipoSolicitud("1");

		Estatus estatus = new Estatus();
		estatus.setCodigo(1);
		estatus.setEstatus("Aprobado");
		estatus.setFechaCambio(new Date(0));
		estatus.setIdEstatus(1);
		estatus.setIdSolicitud("1");
		estatus.setMotivo("Test");

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(solicitud);
		when(estatusRepository.findTopByIdSolicitudOrderByIdEstatusDesc(Mockito.anyString())).thenReturn(estatus);

		assertNotNull(dispersionService.dispersarCredito(DISPERSIONMODEL));
	}

	@Test
	void test_02_dispersarCredito() {

		assertThrows(CustomException.class, () -> {
			dispersionService.dispersarCredito(DISPERSIONMODEL);
		});
	}

	@Test
	void test_03_dispersarCredito() {
		Solicitudes solicitud = new Solicitudes();
		solicitud.setCreatedAt(new Timestamp(0));
		solicitud.setEmpresa("1");
		solicitud.setFechaSolicitud(new Date(0));
		solicitud.setFrecuencia("Semanal");
		solicitud.setIdCliente(1);
		solicitud.setIdRegistro(1);
		solicitud.setIdSolicitud("1");
		solicitud.setIdTipoSolicitud("1");
		solicitud.setMonto(200.0);
		solicitud.setPlazo(1);
		solicitud.setProducto("Test");
		solicitud.setPromotor("1");
		solicitud.setTasa(10.0);
		solicitud.setTipoSolicitud("1");

		Estatus estatus = new Estatus();
		estatus.setCodigo(1);
		estatus.setEstatus("Rechazado");
		estatus.setFechaCambio(new Date(0));
		estatus.setIdEstatus(1);
		estatus.setIdSolicitud("1");
		estatus.setMotivo("Test");

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(solicitud);
		when(estatusRepository.findTopByIdSolicitudOrderByIdEstatusDesc(Mockito.anyString())).thenReturn(estatus);

		assertThrows(CustomException.class, () -> {
			dispersionService.dispersarCredito(DISPERSIONMODEL);
		});
	}

}
