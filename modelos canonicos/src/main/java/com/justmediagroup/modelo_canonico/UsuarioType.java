package com.justmediagroup.modelo_canonico;

/**
 * @author jesus
 *
 */

import java.util.Date;
import java.util.UUID;

public class UsuarioType {

	UUID id;
	String nombreCompleto;
	String correo;
	String password;
	Estado estadoUsuario;
	Date fechaCreacion;
	Date fechaModificacion;
	int intentosLogin;

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
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the estadoUsuario
	 */
	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	/**
	 * @param estadoUsuario the estadoUsuario to set
	 */
	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
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
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the intentosLogin
	 */
	public int getIntentosLogin() {
		return intentosLogin;
	}

	/**
	 * @param intentosLogin the intentosLogin to set
	 */
	public void setIntentosLogin(int intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

}
