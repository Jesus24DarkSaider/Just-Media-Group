package com.justmediagroup.ms.auditoriausuario.service.command.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.exception.TipoError;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.ValorConfigurableDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class ConsultarValorConfigurableCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ConsultarValorConfigurableCommand.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {

		LOG.info(AuditoriaUsuarioConstans.separador);

		ResponseEntity<ValorConfigurableDto> response = null;

		GenericStringParam genericStringParam = (GenericStringParam) parametro;

		try {
			LOG.info("INICIA COMANDO CONSULTAR VALORES CONFIGURABLES");
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			HttpEntity<Object> request = new HttpEntity<>(header);
			/*
			 * Realizamos la peticion HTTP GET al servicio usuario a la capacidad de
			 * consultar valores configurables
			 */
			response = restTemplate.exchange(
					hostService.concat(AuditoriaUsuarioConstans.CONSULTAR_VALOR_CONFIGURABLE_POR_NOMBRE),
					HttpMethod.GET, request, ValorConfigurableDto.class,
					genericStringParam.getValues().get("valorConfigurable"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO : \n" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMANDO CONSULTAR VALORES CONFIGURABLE: {} {}", e.getMessage(),
					e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " + response.getStatusCodeValue() + " -> "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
			throw new BusinessException(
					String.format(AuditoriaUsuarioConstans.MENSAJE_ERROR_CONSULTA, "CONSULTAR VALORES CONFIGURABLES"),
					TipoError.FUENTE_DE_DATOS);
		}
		LOG.info("FINALIZA COMANDO CONSULTAR VALORES CONFIGURABLES");
		LOG.info(AuditoriaUsuarioConstans.separador);
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}
}
