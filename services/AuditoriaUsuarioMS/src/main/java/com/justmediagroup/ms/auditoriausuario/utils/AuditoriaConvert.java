package com.justmediagroup.ms.auditoriausuario.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AuditoriaConvert {

	private static ObjectMapper mapperJson = new ObjectMapper();	
	
	public static String convertirObjetoAString(Object objeto) {
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			return mapperJson.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			
		}
		return "";
	}

}
