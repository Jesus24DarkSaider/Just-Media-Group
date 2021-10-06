package com.justmediagroup.ms.auditoriausuario.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class OpenApiDocumentation {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title("AuditoriaUsuarioMS").description("Microservicio Rest Auditoria Usuario MS")
				.termsOfService("https://www.justmediagroup.com/").version("1.0.0")
				.license(new License().name("Apache 2.0").url("https://www.gnu.org/licenses/gpl-3.0.html"))
				.contact(new io.swagger.v3.oas.models.info.Contact().email("api@justmediagroup.com")));
	}

}
