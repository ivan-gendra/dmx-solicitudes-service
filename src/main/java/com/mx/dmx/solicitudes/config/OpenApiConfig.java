package com.mx.dmx.solicitudes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {

		final String apiTitle = "solicitudes-service";
		return new OpenAPI()
				.info(new Info().title(apiTitle).version("/v1").contact(new Contact().email("ivanmontiel@gendra.dev")));
	}

}
