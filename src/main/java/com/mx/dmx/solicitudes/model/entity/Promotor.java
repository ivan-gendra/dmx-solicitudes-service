package com.mx.dmx.solicitudes.model.entity;

import java.io.Serializable;
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
@Table(name = "promotor")
public class Promotor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro_promotor")
	private Integer idRegistroPromotor;

	@NotNull(message = "El campo id_promotor no puede ser nulo.")
	@Column(name = "id_promotor")
	private String idPromotor;

	@NotNull(message = "El campo nombre no puede ser nulo.")
	@Column(name = "nombre")
	private String nombre;

	@NotNull(message = "El campo apellido_paterno no puede ser nulo.")
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@NotNull(message = "El campo email no puede ser nulo.")
	@Column(name = "email")
	private String email;

	@NotNull(message = "El campo created_at no puede ser nulo.")
	@Column(name = "created_at")
	private Timestamp createdAt;

}
