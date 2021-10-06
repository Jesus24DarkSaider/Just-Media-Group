/**
 * 
 */
package com.justmediagroup.ms.auditoriausuario.service.command.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * @author jesus
 *
 *         Comando de consumo sirve para consultar el usuario por email al
 *         servicio de entidad UsuarioES
 */
@Component
public class ConsultarUsuarioPorEmailCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ConsultarUsuarioPorEmailCommand.class);

	@Value("${uri.usuarioes}")
	String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);

		GenericStringParam genericString = (GenericStringParam) parametro;
		ResponseEntity<UsuarioDto> response = null;

		try {
			LOG.info("INICIA COMANDO CONSULTAR USUARIO POR EMAIL COMMAND");
			// Definimos los objetos que vamos a utilizar para el consumo del servicio
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			headers.set("password", genericString.getValues().get("password"));
			HttpEntity<Object> request = new HttpEntity<>(headers);
			/*
			 * REALIZAMOS LA PETICION HTTP GET AL SERVICIO USUARIOES A LA CAPACIDAD
			 * CONSULTAR USUARIO POR EMAIL
			 */
			response = restTemplate.exchange(hostService.concat(AuditoriaUsuarioConstans.CONSULTAR_USUARIO_POR_EMAIL),
					HttpMethod.GET, request, UsuarioDto.class, genericString.getValues().get("correo"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMAND CONSULTAR EMPLEADO POR EMAIL: {} {}", e.getMessage(), e.getStackTrace());
			// LOG.error("ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " +
			// response.getStatusCodeValue() + " "
			// + AuditoriaConvert.convertirObjetoAString(response.getBody()));
			return new UsuarioDto();
		}
		LOG.info("FINALIZA COMANDO CONSULTAR USUARIO POR EMAIL COMMAND");
		LOG.info(AuditoriaUsuarioConstans.separador);
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
