/**
 * 
 */
package com.justmediagroup.ms.auditoriausuario.service.command.consumer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.IntentosLoginDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

/**
 * @author jesus
 *
 */
@Component
public class ConsultarIntentosLoginCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ConsultarUsuarioPorEmailCommand.class);

	@Value("${uri.usuarioes}")
	String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);

		GenericStringParam genericString = (GenericStringParam) parametro;
		ResponseEntity<List<IntentosLoginDto>> response = null;

		try {
			LOG.info("INICIA COMANDO CONSULTAR INTENTOS LOGIN");
			// Definimos los objetos que vamos a utilizar para el consumo del servicio
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			HttpEntity<Object> request = new HttpEntity<>(headers);
			/*
			 * REALIZAMOS LA PETICION HTTP GET AL SERVICIO USUARIOES A LA CAPACIDAD
			 * CONSULTAR INTENTOS LOGIN
			 */
			response = restTemplate.exchange(
					hostService.concat(AuditoriaUsuarioConstans.CONSULTAR_INTENTOS_LOGIN_POR_CORREO), HttpMethod.GET,
					request, new ParameterizedTypeReference<List<IntentosLoginDto>>() {
					}, genericString.getValues().get("correo"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

			if (response.getStatusCodeValue() == 404) {
				return new ArrayList<>();
			}

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMANDO CONSULTAR INTENTOS LOGGIN: {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " + response.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
			return new ArrayList<>();
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
