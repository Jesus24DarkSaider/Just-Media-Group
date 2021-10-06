package com.justmediagroup.es.usuario.utils.convert;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justmediagroup.es.usuario.repository.model.ValoresConfigurables;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

public class ValorConfigurableConvert {

	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioConvert.class);

	private static ObjectMapper mapperJson = new ObjectMapper();;

	ValorConfigurableConvert() {
	}

	public static ValoresConfigurablesType convertModelToType(ValoresConfigurables valorConfig) {
		LOGGER.info("ValoresConfigurables MODEL TO TYPE");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(valorConfig));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(valorConfig, ValoresConfigurablesType.class);
	}

	public static ValoresConfigurables convertTypeToModel(ValoresConfigurablesType valorConfig) {
		LOGGER.info("ValoresConfigurables TYPE TO MODEL");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(valorConfig));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(valorConfig, ValoresConfigurables.class);
	}

}
