package com.mx.dmx.solicitudes.model.entity;

import java.io.Serializable;

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
@Table(name = "dispersion")
public class Dispersion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dispersion")
	private Integer idDispersion;

	@NotNull(message = "El campo id_solicitud no puede ser nulo.")
	@Column(name = "id_solicitud")
	private String idSolicitud;

	@NotNull(message = "El campo id_crredito no puede ser nulo.")
	@Column(name = "id_crredito")
	private Integer idCrredito;

	@NotNull(message = "El campo capital_dispersado no puede ser nulo.")
	@Column(name = "capital_dispersado")
	private Double capitalDispersado;

	@NotNull(message = "El campo monto no puede ser nulo.")
	@Column(name = "monto")
	private Double monto;

	@NotNull(message = "El campo tasa no puede ser nulo.")
	@Column(name = "tasa")
	private Double tasa;

	@NotNull(message = "El campo plazo no puede ser nulo.")
	@Column(name = "plazo")
	private Double plazo;

	@NotNull(message = "El campo frecuencia no puede ser nulo.")
	@Column(name = "frecuencia")
	private String frecuencia;

}
