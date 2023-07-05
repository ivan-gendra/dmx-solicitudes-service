package com.mx.dmx.solicitudes.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.ClienteModel;
import com.mx.dmx.solicitudes.model.dto.MotivoModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.model.dto.SolicitudModel;
import com.mx.dmx.solicitudes.model.entity.Promotor;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;
import com.mx.dmx.solicitudes.repository.PromotorRepository;
import com.mx.dmx.solicitudes.repository.SolicitudesRepository;
import com.mx.dmx.solicitudes.service.SolicitudesService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
public class SolicitudesServiceTest {

	@Autowired
	SolicitudesService solicitudesService;

	@MockBean
	SolicitudesRepository solicitudesRepository;

	@MockBean
	PromotorRepository promotorRepository;

	private static final RegistroModel REGISTROMODEL = new RegistroModel();
	private static final ActualizarSolicitudModel ACTUALIZARSOLICITUDMODEL = new ActualizarSolicitudModel();
	private static final ActualizarSolicitudModel ACTUALIZARSOLICITUDMODELERROR = new ActualizarSolicitudModel();

	@BeforeAll
	static void setUp() {
		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setApellidoMaterno("Ordoñez");
		clienteModel.setApellidoPaterno("Montiel");
		clienteModel.setNombre("Jose Ivan");

		SolicitudModel solicitudModel = new SolicitudModel();
		solicitudModel.setFechaSolicitud(new Date(0));
		solicitudModel.setFrecuencia("Semanal");
		solicitudModel.setIdSolicitud("1");
		solicitudModel.setIdTipoSolicitud("1");
		solicitudModel.setMonto(100.0);
		solicitudModel.setPlazo(1);
		solicitudModel.setProducto("1");
		solicitudModel.setProducto("Test");
		solicitudModel.setTasa(16.0);
		solicitudModel.setTipoSolicitud("Test");

		REGISTROMODEL.setCliente(clienteModel);
		REGISTROMODEL.setEmpresa("1");
		REGISTROMODEL.setPromotor("1");
		REGISTROMODEL.setSolicitud(solicitudModel);

		MotivoModel motivModel = new MotivoModel();
		motivModel.setCodigo(1);
		motivModel.setMotivo("Test");
		ArrayList<MotivoModel> listMotivo = new ArrayList<>();
		listMotivo.add(motivModel);

		ACTUALIZARSOLICITUDMODEL.setEstatus("Aprobado");
		ACTUALIZARSOLICITUDMODEL.setFechaCambio(new Date(0));
		ACTUALIZARSOLICITUDMODEL.setIdSolicitud("1");
		ACTUALIZARSOLICITUDMODEL.setMotivo(listMotivo);

		ACTUALIZARSOLICITUDMODELERROR.setEstatus("Test");
		ACTUALIZARSOLICITUDMODELERROR.setFechaCambio(new Date(0));
		ACTUALIZARSOLICITUDMODELERROR.setIdSolicitud("1");
		ACTUALIZARSOLICITUDMODELERROR.setMotivo(listMotivo);
	}

	@Test
	void test_01_agregarSolicitud() {
		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno("Test");
		promotor.setApellidoPaterno("Test");
		promotor.setCreatedAt(new Timestamp(0));
		promotor.setEmail("algo@algo.com");
		promotor.setIdPromotor("1");
		promotor.setIdRegistroPromotor(1);
		promotor.setNombre("Test");

		when(promotorRepository.findByIdPromotor(Mockito.anyString())).thenReturn(promotor);

		assertNotNull(solicitudesService.agregarSolicitud(REGISTROMODEL));

	}

	@Test
	void test_02_agregarSolicitud() {
		when(promotorRepository.findByIdPromotor(Mockito.anyString())).thenReturn(null);

		assertThrows(CustomException.class, () -> {
			solicitudesService.agregarSolicitud(REGISTROMODEL);
		});

	}

	@Test
	void test_03_agregarSolicitud() {
		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno("Test");
		promotor.setApellidoPaterno("Test");
		promotor.setCreatedAt(new Timestamp(0));
		promotor.setEmail("algo@algo.com");
		promotor.setIdPromotor("1");
		promotor.setIdRegistroPromotor(1);
		promotor.setNombre("Test");

		Solicitudes solicitud = new Solicitudes();
		solicitud.setCreatedAt(new Timestamp(0));
		solicitud.setEmpresa("1");
		solicitud.setFechaSolicitud(new Date(0));
		solicitud.setFrecuencia("Semanal");
		solicitud.setIdCliente(1);
		solicitud.setIdRegistro(1);
		solicitud.setIdSolicitud("1");
		solicitud.setIdTipoSolicitud("Test");
		solicitud.setMonto(300.0);
		solicitud.setPlazo(1);
		solicitud.setProducto("Test");
		solicitud.setPromotor("1");
		solicitud.setTasa(10.0);
		solicitud.setTipoSolicitud("Test");

		when(promotorRepository.findByIdPromotor(Mockito.anyString())).thenReturn(promotor);

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(solicitud);

		assertThrows(CustomException.class, () -> {
			solicitudesService.agregarSolicitud(REGISTROMODEL);
		});

	}

	@Test
	void test_04_actualizarSolicitud() {

		Promotor promotor = new Promotor();
		promotor.setApellidoMaterno("Test");
		promotor.setApellidoPaterno("Test");
		promotor.setCreatedAt(new Timestamp(0));
		promotor.setEmail("algo@algo.com");
		promotor.setIdPromotor("1");
		promotor.setIdRegistroPromotor(1);
		promotor.setNombre("Test");

		Solicitudes solicitud = new Solicitudes();
		solicitud.setCreatedAt(new Timestamp(0));
		solicitud.setEmpresa("1");
		solicitud.setFechaSolicitud(new Date(0));
		solicitud.setFrecuencia("Semanal");
		solicitud.setIdCliente(1);
		solicitud.setIdRegistro(1);
		solicitud.setIdSolicitud("1");
		solicitud.setIdTipoSolicitud("Test");
		solicitud.setMonto(300.0);
		solicitud.setPlazo(1);
		solicitud.setProducto("Test");
		solicitud.setPromotor("1");
		solicitud.setTasa(10.0);
		solicitud.setTipoSolicitud("Test");

		when(promotorRepository.findByIdPromotor(Mockito.anyString())).thenReturn(promotor);

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(solicitud);

		assertNotNull(solicitudesService.actualizarSolicitud(ACTUALIZARSOLICITUDMODEL));

	}

	@Test
	void test_05_actualizarSolicitud() {

		assertThrows(CustomException.class, () -> {
			solicitudesService.actualizarSolicitud(ACTUALIZARSOLICITUDMODELERROR);
		});

	}

	@Test
	void test_06_actualizarSolicitud() {

		assertThrows(CustomException.class, () -> {
			solicitudesService.actualizarSolicitud(ACTUALIZARSOLICITUDMODEL);
		});

	}

	void test_07_buscarSolicitud() {
		Solicitudes solicitud = new Solicitudes();
		solicitud.setCreatedAt(new Timestamp(0));
		solicitud.setEmpresa("1");
		solicitud.setFechaSolicitud(new Date(0));
		solicitud.setFrecuencia("Semanal");
		solicitud.setIdCliente(1);
		solicitud.setIdRegistro(1);
		solicitud.setIdSolicitud("1");
		solicitud.setIdTipoSolicitud("Test");
		solicitud.setMonto(300.0);
		solicitud.setPlazo(1);
		solicitud.setProducto("Test");
		solicitud.setPromotor("1");
		solicitud.setTasa(10.0);
		solicitud.setTipoSolicitud("Test");

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(solicitud);

		assertNotNull(solicitudesService.buscarSolicitud("1"));
	}

	void test_08_buscarSolicitud() {

		when(solicitudesRepository.findByIdSolicitud(Mockito.anyString())).thenReturn(null);

		assertThrows(CustomException.class, () -> {
			solicitudesService.buscarSolicitud("1");
		});
	}
}
