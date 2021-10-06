package com.justmediagroup.ms.auditoriausuario.controller.dto;

import java.util.HashMap;
import java.util.Map;

import com.justmediagroup.logica_comun.patrones.command.IParam;

public class GenericStringParam implements IParam {

	private Map<String, String> values = new HashMap<>();

	/**
	 * @return the values
	 */
	public Map<String, String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public GenericStringParam addValue(String key, String values) {
		this.values.put(key, values);
		return this;
	}

}
