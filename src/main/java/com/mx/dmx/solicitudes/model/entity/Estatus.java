package com.mx.dmx.solicitudes.model.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "estatus")
public class Estatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estatus")
	private Integer idEstatus;

	@NotNull(message = "El campo id_solicitud no puede ser nulo.")
	@Column(name = "id_solicitud")
	private String idSolicitud;

	@NotNull(message = "El campo motivo no puede ser nulo.")
	@Column(name = "motivo")
	private String motivo;

	@NotNull(message = "El campo codigo no puede ser nulo.")
	@Column(name = "codigo")
	private Integer codigo;

	@NotNull(message = "El campo estatus no puede ser nulo.")
	@Column(name = "estatus")
	private String estatus;

	@NotNull(message = "El campo fecha_cambio no puede ser nulo.")
	@Column(name = "fecha_cambio")
	private Date fechaCambio;

}
