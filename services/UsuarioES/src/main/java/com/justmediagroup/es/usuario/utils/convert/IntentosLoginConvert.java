package com.justmediagroup.es.usuario.utils.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justmediagroup.es.usuario.repository.model.IntentosLogin;
import com.justmediagroup.modelo_canonico.IntentosLoginType;

public class IntentosLoginConvert {

	private static Logger LOGGER = LoggerFactory.getLogger(IntentosLoginConvert.class);

	private static ObjectMapper mapperJson = new ObjectMapper();

	IntentosLoginConvert() {
	}

	public static IntentosLoginType convertModelToType(IntentosLogin intentosLogin) {
		LOGGER.info("IntentosLogin MODEL TO TYPE");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(intentosLogin));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(intentosLogin, IntentosLoginType.class);
	}

	public static IntentosLogin convertTypeToModel(IntentosLoginType intentosLoginType) {
		LOGGER.info("IntentosLogin TYPE TO MODEL");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(intentosLoginType));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(intentosLoginType, IntentosLogin.class);
	}

	public static List<IntentosLoginType> convertlistTypeToModelx(List<IntentosLogin> listadoIntentosLogin) {
		return listadoIntentosLogin.stream().map(IntentosLogin -> (convertModelToType(IntentosLogin)))
				.collect(Collectors.toList());
	}

}
