package com.mx.dmx.solicitudes.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.service.PromotorService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
public class PromotorServiceTest {

	@Autowired
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
	void test_01_getAllPromotor() {
		assertNotNull(promotorService.getAllPromotor());
	}

	@Test
	void test_02_getAllPromotor() {
		assertNotNull(promotorService.addNewPromotor(AGREGARPROMOTORMODEL));
	}
}
