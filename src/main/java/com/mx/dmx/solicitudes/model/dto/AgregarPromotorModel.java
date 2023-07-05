package com.mx.dmx.solicitudes.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgregarPromotorModel {

	@Schema(description = "Id con el que se reconocerá al promotor que se da de alta")
	private String idPromotor;
	@Schema(description = "Nombre del promotor")
	private String nombre;
	@Schema(description = "Apellido paterno del promotor")
	private String apellidoPaterno;
	@Schema(description = "Apellido materno del promotor")
	private String apellidMaterno;
	@Schema(description = "Correo del promotor")
	private String email;

}
