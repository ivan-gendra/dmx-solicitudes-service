package com.mx.dmx.solicitudes.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.controller.PromotorController;
import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.model.entity.Promotor;
import com.mx.dmx.solicitudes.service.PromotorService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
class PromotorControllerTest {

	@Autowired
	PromotorController promotorController;

	@MockBean
	PromotorService promotorService;

	private static final AgregarPromotorModel AGREGARPROMOTORMODEL = new AgregarPromotorModel();

	@BeforeAll
	static void setUp() {
		AGREGARPROMOTORMODEL.setApellidMaterno("Test");
		AGREGARPROMOTORMODEL.setApellidoPaterno("Test");
		AGREGARPROMOTORMODEL.setEmail("algo@algo.com");
		AGREGARPROMOTORMODEL.setIdPromotor("1");
		AGREGARPROMOTORMODEL.setNombre("Test");
	}

	@Test
	void test_01_getPromotor() {
		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno("Test");
		promotor.setApellidoPaterno("Test");
		promotor.setCreatedAt(new Timestamp(0));
		promotor.setEmail("algo@algo.com");
		promotor.setIdPromotor("1");
		promotor.setIdRegistroPromotor(1);
		promotor.setNombre("Test");
		List<Promotor> listPromotor = new ArrayList<>();
		listPromotor.add(promotor);
		when(promotorService.getAllPromotor()).thenReturn(listPromotor);
		assertEquals(HttpStatus.OK, promotorController.getPromotor().getStatusCode());
	}

	@Test
	void test_02_agregarPromotor() {
		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno("Test");
		promotor.setApellidoPaterno("Test");
		promotor.setCreatedAt(new Timestamp(0));
		promotor.setEmail("algo@algo.com");
		promotor.setIdPromotor("1");
		promotor.setIdRegistroPromotor(1);
		promotor.setNombre("Test");
		when(promotorService.addNewPromotor(Mockito.any())).thenReturn(promotor);
		assertEquals(HttpStatus.OK, promotorController.agregarPromotor(AGREGARPROMOTORMODEL).getStatusCode());
	}

}
