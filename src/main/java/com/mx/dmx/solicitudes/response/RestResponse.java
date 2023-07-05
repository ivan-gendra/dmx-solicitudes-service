package com.mx.dmx.solicitudes.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {

	private Boolean success;
	private Integer code;
	private String message;
	private Object payload;

}