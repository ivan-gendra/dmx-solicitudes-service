package com.mx.dmx.solicitudes.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.mx.dmx.solicitudes.controller.SolicitudesController;
import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.ClienteModel;
import com.mx.dmx.solicitudes.model.dto.MotivoModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.model.dto.RespuestaActualizar;
import com.mx.dmx.solicitudes.model.dto.RespuestaRegistroSolicitud;
import com.mx.dmx.solicitudes.model.dto.SolicitudModel;
import com.mx.dmx.solicitudes.model.entity.Clientes;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;
import com.mx.dmx.solicitudes.service.SolicitudesService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
class SolicitudesControllerTest {

	@Autowired
	SolicitudesController solicitudesController;

	@MockBean
	SolicitudesService solicitudesService;

	private static final RegistroModel REGISTROMODEL = new RegistroModel();
	private static final ActualizarSolicitudModel ACTUALIZARSOLICITUDMODEL = new ActualizarSolicitudModel();

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

	}

	@Test
	void test_01_registrarSolicitud() {
		Clientes cliente = new Clientes();
		cliente.setApellidoMaterno("Test");
		cliente.setApellidoPaterno("Test");
		cliente.setCreatedAt(new Timestamp(0));
		cliente.setIdCliente(1);
		cliente.setNombre("Test");

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

		RespuestaRegistroSolicitud respuesta = new RespuestaRegistroSolicitud();
		respuesta.setCliente(cliente);
		respuesta.setSolicitud(solicitud);

		when(solicitudesService.agregarSolicitud(Mockito.any())).thenReturn(respuesta);
		assertEquals(HttpStatus.OK, solicitudesController.registrarSolicitud(REGISTROMODEL).getStatusCode());
	}

	@Test
	void test_02_registrarSolicitud() {

		when(solicitudesService.agregarSolicitud(Mockito.any()))
				.thenThrow(new CustomException("Error", HttpStatus.CONFLICT, "waring"));

		assertEquals(HttpStatus.CONFLICT, solicitudesController.registrarSolicitud(REGISTROMODEL).getStatusCode());
	}

	@Test
	void test_03_registrarSolicitud() {

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

		when(solicitudesService.buscarSolicitud(Mockito.anyString())).thenReturn(solicitud);
		assertEquals(HttpStatus.OK, solicitudesController.getSolicitud("1").getStatusCode());
	}

	@Test
	void test_04_registrarSolicitud() {

		when(solicitudesService.buscarSolicitud(Mockito.anyString()))
				.thenThrow(new CustomException("Error", HttpStatus.CONFLICT, "waring"));

		assertEquals(HttpStatus.CONFLICT, solicitudesController.getSolicitud("1").getStatusCode());
	}

	@Test
	void test_05_registrarSolicitud() {
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

		RespuestaActualizar respuesta = new RespuestaActualizar();
		respuesta.setSolicitud(solicitud);

		when(solicitudesService.actualizarSolicitud(Mockito.any())).thenReturn(respuesta);
		assertEquals(HttpStatus.OK,
				solicitudesController.actualizarSolicitud(ACTUALIZARSOLICITUDMODEL).getStatusCode());
	}

	@Test
	void test_06_registrarSolicitud() {

		when(solicitudesService.actualizarSolicitud(Mockito.any()))
				.thenThrow(new CustomException("Error", HttpStatus.CONFLICT, "waring"));

		assertEquals(HttpStatus.CONFLICT,
				solicitudesController.actualizarSolicitud(ACTUALIZARSOLICITUDMODEL).getStatusCode());
	}

}
