package com.justmediagroup.modelo_canonico;

import java.util.Date;
import java.util.UUID;

public class IntentosLoginType {

	private UUID id;

	private Estado estado;

	private Date fechaCreacion;

	private String correoUsuario;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the correoUsuario
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}

	/**
	 * @param correoUsuario the correoUsuario to set
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

}
