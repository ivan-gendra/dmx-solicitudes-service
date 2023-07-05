package com.mx.dmx.solicitudes.test.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.AgregarPromotorModel;
import com.mx.dmx.solicitudes.model.dto.ClienteModel;
import com.mx.dmx.solicitudes.model.dto.DispersionModel;
import com.mx.dmx.solicitudes.model.dto.MotivoModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.model.dto.RespuestaActualizar;
import com.mx.dmx.solicitudes.model.dto.RespuestaRegistroSolicitud;
import com.mx.dmx.solicitudes.model.dto.SolicitudModel;
import com.mx.dmx.solicitudes.model.entity.Clientes;
import com.mx.dmx.solicitudes.model.entity.Dispersion;
import com.mx.dmx.solicitudes.model.entity.Estatus;
import com.mx.dmx.solicitudes.model.entity.Promotor;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;

@SpringBootTest
class EntityTest {

	@Test
	void testAllGetterSetter() {
		BeanTester beanTester = new BeanTester();
		Configuration responseActualizarSolicitudModel = new ConfigurationBuilder().iterations(2)
				.ignoreProperty("fechaCambio").build();
		beanTester.testBean(ActualizarSolicitudModel.class, responseActualizarSolicitudModel);
		beanTester.testBean(AgregarPromotorModel.class);
		beanTester.testBean(ClienteModel.class);
		beanTester.testBean(DispersionModel.class);
		beanTester.testBean(MotivoModel.class);
		Configuration responseRegistroModel = new ConfigurationBuilder().iterations(2).ignoreProperty("cliente")
				.ignoreProperty("solicitud").build();
		beanTester.testBean(RegistroModel.class, responseRegistroModel);
		Configuration responseActualizarRespuestaActualziar = new ConfigurationBuilder().iterations(2)
				.ignoreProperty("solicitud").build();
		beanTester.testBean(RespuestaActualizar.class, responseActualizarRespuestaActualziar);
		Configuration responseRegistroRespuesta = new ConfigurationBuilder().iterations(2).ignoreProperty("cliente")
				.ignoreProperty("solicitud").build();
		beanTester.testBean(RespuestaRegistroSolicitud.class, responseRegistroRespuesta);
		Configuration responseSolicitudModel = new ConfigurationBuilder().iterations(2).ignoreProperty("fechaSolicitud")
				.build();
		beanTester.testBean(SolicitudModel.class, responseSolicitudModel);
		Configuration responseCliente = new ConfigurationBuilder().iterations(2).ignoreProperty("createdAt").build();
		beanTester.testBean(Clientes.class, responseCliente);
		beanTester.testBean(Dispersion.class);
		Configuration responseEstatus = new ConfigurationBuilder().iterations(2).ignoreProperty("fechaCambio").build();
		beanTester.testBean(Estatus.class, responseEstatus);
		Configuration responsePromotor = new ConfigurationBuilder().iterations(2).ignoreProperty("createdAt").build();
		beanTester.testBean(Promotor.class, responsePromotor);
		Configuration responseSolicitud = new ConfigurationBuilder().iterations(2).ignoreProperty("fechaSolicitud")
				.ignoreProperty("createdAt").build();
		beanTester.testBean(Solicitudes.class, responseSolicitud);
		assertNotNull(beanTester);
	}
}
