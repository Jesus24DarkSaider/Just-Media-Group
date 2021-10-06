package com.justmediagroup.logica_comun.utilitarios;

public class RespuestaDTO {

	private String codigoRespuesta;
	private String descripcion;

	/**
	 * 
	 */
	public RespuestaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigoRespuesta
	 * @param descripcion
	 */
	public RespuestaDTO(String codigoRespuesta, String descripcion) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
