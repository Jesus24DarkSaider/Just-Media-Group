package com.justmediagroup.logica_comun.constantes;

public enum EstadoProcesamiento {
	
	EVALUAR_ALMUERZO("EVALUAR MARCACION ALMUERZO"),
	EVALUAR_SALIDA("EVALUAR MARCACION SALIDA"),
	ALMUERZO_PROCESADO("ALMUERZO PROCESADO"),
	SALIDA_PROCESADA("SALIDA PROCESADA");
	
	private String value;
	
	EstadoProcesamiento(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
