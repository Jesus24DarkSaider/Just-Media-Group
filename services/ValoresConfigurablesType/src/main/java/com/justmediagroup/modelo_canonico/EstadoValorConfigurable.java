package com.justmediagroup.modelo_canonico;

public enum EstadoValorConfigurable {

	ACTIVO("ACTIVO"), INACTIVO("INACTIVO");

	private String value;

	EstadoValorConfigurable (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static EstadoValorConfigurable  fromValue(String value) {
		for (EstadoValorConfigurable  b : EstadoValorConfigurable .values()) {
			if (b.value.equals(value)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}

}
