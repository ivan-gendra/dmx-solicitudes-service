package com.mx.dmx.solicitudes.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "solicitudes")
public class Solicitudes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private Integer idRegistro;

	@NotNull(message = "El campo id_solicitud no puede ser nulo.")
	@Column(name = "id_solicitud")
	private String idSolicitud;

	@NotNull(message = "El campo created_at no puede ser nulo.")
	@Column(name = "created_at")
	private Timestamp createdAt;

	@NotNull(message = "El campo monto no puede ser nulo.")
	@Column(name = "monto")
	private Double monto;

	@NotNull(message = "El campo producto no puede ser nulo.")
	@Column(name = "producto")
	private String producto;

	@NotNull(message = "El campo tipo_solicitud no puede ser nulo.")
	@Column(name = "tipo_solicitud")
	private String tipoSolicitud;

	@NotNull(message = "El campo id_tipo_solicitud no puede ser nulo.")
	@Column(name = "id_tipo_solicitud")
	private String idTipoSolicitud;

	@NotNull(message = "El campo tasa no puede ser nulo.")
	@Column(name = "tasa")
	private Double tasa;

	@NotNull(message = "El campo plazo no puede ser nulo.")
	@Column(name = "plazo")
	private Integer plazo;

	@NotNull(message = "El campo frecuencia no puede ser nulo.")
	@Column(name = "frecuencia")
	private String frecuencia;

	@NotNull(message = "El campo fecha_solicitud no puede ser nulo.")
	@Column(name = "fecha_solicitud")
	private Date fechaSolicitud;

	@NotNull(message = "El campo empresa no puede ser nulo.")
	@Column(name = "empresa")
	private String empresa;

	@NotNull(message = "El campo promotor no puede ser nulo.")
	@Column(name = "promotor")
	private String promotor;

	@NotNull(message = "El campo id_cliente no puede ser nulo.")
	@Column(name = "id_cliente")
	private Integer idCliente;
}
