package com.justmediagroup.logica_comun.constantes;


public enum TipoControl {

	MARCACION("MARCACION"), ESFUERZO_LABORAL("ESFUERZO LABORAL");
	
	private String value;
	
	TipoControl(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
