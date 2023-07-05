package com.mx.dmx.solicitudes.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.controller.DispersionController;
import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.DispersionModel;
import com.mx.dmx.solicitudes.model.entity.Dispersion;
import com.mx.dmx.solicitudes.service.DispersionService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
public class DispersionControllerTest {

	@Autowired
	DispersionController dispersionController;

	@MockBean
	DispersionService dispersionService;

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
	void test_01_disperar() {
		Dispersion dispersion = new Dispersion();
		dispersion.setCapitalDispersado(100.0);
		dispersion.setFrecuencia("Semanal");
		dispersion.setIdCrredito(1);
		dispersion.setIdDispersion(1);
		dispersion.setIdSolicitud("1");
		dispersion.setMonto(10.0);
		dispersion.setPlazo(10.0);
		dispersion.setTasa(10.0);

		when(dispersionService.dispersarCredito(Mockito.any())).thenReturn(dispersion);
		assertEquals(HttpStatus.OK, dispersionController.disperar(DISPERSIONMODEL).getStatusCode());
	}

	@Test
	void test_02_disperar() {
		when(dispersionService.dispersarCredito(Mockito.any()))
				.thenThrow(new CustomException("Error", HttpStatus.CONFLICT, "waring"));
		assertEquals(HttpStatus.CONFLICT, dispersionController.disperar(DISPERSIONMODEL).getStatusCode());
	}

}
