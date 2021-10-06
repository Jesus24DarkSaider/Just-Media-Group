package com.justmediagroup.ms.auditoriausuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuditoriaUsuarioMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditoriaUsuarioMsApplication.class, args);
	}

}
