package com.justmediagroup.us.elasticservicegatewayus.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.justmediagroup.logica_comun.constantes.MensajeDelSistema;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;

public class UtilityServiceValidator {

	public static final ResponseEntity<Object> validarResultado(DatosAuditoriaUsuario resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("201")
				.descripcion(String.format("RECURSO CREADO CON ID [%s]", resultado.getId())), HttpStatus.CREATED);
	}

}
