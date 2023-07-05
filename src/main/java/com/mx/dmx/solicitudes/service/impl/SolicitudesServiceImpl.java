package com.mx.dmx.solicitudes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.dmx.solicitudes.exception.CustomException;
import com.mx.dmx.solicitudes.model.dto.ActualizarSolicitudModel;
import com.mx.dmx.solicitudes.model.dto.ClienteModel;
import com.mx.dmx.solicitudes.model.dto.RegistroModel;
import com.mx.dmx.solicitudes.model.dto.RespuestaActualizar;
import com.mx.dmx.solicitudes.model.dto.RespuestaRegistroSolicitud;
import com.mx.dmx.solicitudes.model.entity.Clientes;
import com.mx.dmx.solicitudes.model.entity.Estatus;
import com.mx.dmx.solicitudes.model.entity.Promotor;
import com.mx.dmx.solicitudes.model.entity.Solicitudes;
import com.mx.dmx.solicitudes.repository.ClientesRepository;
import com.mx.dmx.solicitudes.repository.EstatusRepository;
import com.mx.dmx.solicitudes.repository.PromotorRepository;
import com.mx.dmx.solicitudes.repository.SolicitudesRepository;
import com.mx.dmx.solicitudes.service.SolicitudesService;
import com.mx.dmx.solicitudes.utils.Helpers;
import com.mx.dmx.solicitudes.utils.SendMessageEmail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SolicitudesServiceImpl implements SolicitudesService {

	@Autowired
	ClientesRepository clientesRepository;

	@Autowired
	SolicitudesRepository solicitudesRepository;

	@Autowired
	PromotorRepository promotorRepository;

	@Autowired
	EstatusRepository estatusRepository;

	@Autowired
	Helpers helpers;

	@Autowired
	SendMessageEmail sendMessageEmail;

	@Value("${html.registro}")
	String registro;

	@Value("${html.actualizar}")
	String actualizar;

	@Value("${code.warning}")
	String warning;

	@Override
	public RespuestaRegistroSolicitud agregarSolicitud(RegistroModel registroModel) {
		log.info("Inicia elproceso de registro para la solicitud {}", registroModel);

		RespuestaRegistroSolicitud respuesta = new RespuestaRegistroSolicitud();

		Solicitudes solicitudesValidar = solicitudesRepository
				.findByIdSolicitud(registroModel.getSolicitud().getIdSolicitud());

		if (solicitudesValidar == null || solicitudesValidar.getIdSolicitud() == null) {

			Promotor promotor = promotorRepository.findByIdPromotor(registroModel.getPromotor());

			if (promotor != null) {
				Clientes cliente = this.getClienteModelo(registroModel.getCliente());

				Solicitudes solicitudes = new Solicitudes();
				solicitudes.setCreatedAt(helpers.getActualDate());
				solicitudes.setEmpresa(registroModel.getEmpresa());
				solicitudes.setFechaSolicitud(registroModel.getSolicitud().getFechaSolicitud());
				solicitudes.setFrecuencia(registroModel.getSolicitud().getFrecuencia());
				solicitudes.setIdCliente(cliente.getIdCliente());
				solicitudes.setIdSolicitud(registroModel.getSolicitud().getIdSolicitud());
				solicitudes.setIdTipoSolicitud(registroModel.getSolicitud().getIdTipoSolicitud());
				solicitudes.setMonto(registroModel.getSolicitud().getMonto());
				solicitudes.setPlazo(registroModel.getSolicitud().getPlazo());
				solicitudes.setProducto(registroModel.getSolicitud().getProducto());
				solicitudes.setPromotor(registroModel.getPromotor());
				solicitudes.setTasa(registroModel.getSolicitud().getTasa());
				solicitudes.setTipoSolicitud(registroModel.getSolicitud().getTipoSolicitud());

				respuesta.setSolicitud(solicitudesRepository.save(solicitudes));
				respuesta.setCliente(cliente);

				String nombreCliente = cliente.getNombre().concat(" ").concat(cliente.getApellidoPaterno()).concat(" ")
						.concat(cliente.getApellidoMaterno());

				String htmlRegistro = String.format(registro, nombreCliente, solicitudes.getIdSolicitud());

				sendMessageEmail.sendEmailSmtp(promotor.getEmail(), htmlRegistro, "Registro de solicitud");

				return respuesta;

			} else {
				throw new CustomException("No existe el promotor que intenta hacer la transacción", HttpStatus.CONFLICT,
						warning);
			}

		} else {
			throw new CustomException("La solicitud que intetna agregar ya existe", HttpStatus.CONFLICT, warning);
		}
	}

	private Clientes getClienteModelo(ClienteModel clienteModel) {
		Clientes cliente = new Clientes();
		cliente.setApellidoMaterno(clienteModel.getApellidoMaterno());
		cliente.setApellidoPaterno(clienteModel.getApellidoPaterno());
		cliente.setCreatedAt(helpers.getActualDate());
		cliente.setNombre(clienteModel.getNombre());
		return clientesRepository.save(cliente);
	}

	@Override
	public Solicitudes buscarSolicitud(String idSolicitud) {
		Solicitudes solicitud = solicitudesRepository.findByIdSolicitud(idSolicitud);

		if (solicitud != null) {
			return solicitud;
		} else {
			throw new CustomException("No existe la solicitud solicitada", HttpStatus.CONFLICT, warning);
		}
	}

	@Override
	public RespuestaActualizar actualizarSolicitud(ActualizarSolicitudModel actualizarSolicitudModel) {
		log.info("Inicia la solicitud de actualización para el modelo {}", actualizarSolicitudModel);
		if (actualizarSolicitudModel.getEstatus().equals("Aprobado")
				|| actualizarSolicitudModel.getEstatus().equals("Rechazado")) {

			RespuestaActualizar respuestaActualizar = new RespuestaActualizar();

			Solicitudes solicitudes = solicitudesRepository
					.findByIdSolicitud(actualizarSolicitudModel.getIdSolicitud());

			if (solicitudes != null) {

				respuestaActualizar.setSolicitud(solicitudes);

				Promotor promotor = promotorRepository.findByIdPromotor(solicitudes.getPromotor());

				String htmlActualizar = String.format(actualizar, solicitudes.getIdSolicitud(),
						actualizarSolicitudModel.getEstatus());

				this.registroEstatus(actualizarSolicitudModel);

				sendMessageEmail.sendEmailSmtp(promotor.getEmail(), htmlActualizar, "Actualización de estatus");

				return respuestaActualizar;
			} else {
				throw new CustomException("No existe la solicitud solicitada", HttpStatus.CONFLICT, "error");
			}
		} else {
			throw new CustomException("El estatus que esta usando es incorrecto (Aprobado/Rechazado)",
					HttpStatus.CONFLICT, warning);
		}

	}

	private void registroEstatus(ActualizarSolicitudModel actualizarSolicitudModel) {
		actualizarSolicitudModel.getMotivo().forEach(m -> {
			Estatus estatus = new Estatus();
			estatus.setCodigo(m.getCodigo());
			estatus.setFechaCambio(actualizarSolicitudModel.getFechaCambio());
			estatus.setIdSolicitud(actualizarSolicitudModel.getIdSolicitud());
			estatus.setEstatus(actualizarSolicitudModel.getEstatus());
			estatus.setMotivo(m.getMotivo());
			estatusRepository.save(estatus);
		});
	}

}
