package com.justmediagroup.us.elasticservicegatewayus.repository.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import nonapi.io.github.classgraph.json.Id;

@Document(indexName = "auditoriausuario", type = "datosauditoria")
public class DatosAuditoriaUsuario {

	@Id
	private String id;

	@Field(type = FieldType.Text, name = "enlace")
	private String enlace;

	@Field(type = FieldType.Text, name = "usuarioId")
	private String usuarioId;

	@Field(type = FieldType.Text, name = "sistema")
	private String sistema;

	@Field(type = FieldType.Text, name = "versionSistemaOperativo")
	private String versionSistemaOperativo;

	@Field(type = FieldType.Text, name = "versionDelNavegador")
	private String versionDelNavegador;

	@Field(type = FieldType.Text, name = "correo")
	private String correo;

	@Field(type = FieldType.Text, name = "password")
	private String password;

	@Field(type = FieldType.Text, name = "canal")
	private String canal;

	/**
	 * @return the enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * @param enlace the enlace to set
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * @return the usuarioId
	 */
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the versionSistemaOperativo
	 */
	public String getVersionSistemaOperativo() {
		return versionSistemaOperativo;
	}

	/**
	 * @param versionSistemaOperativo the versionSistemaOperativo to set
	 */
	public void setVersionSistemaOperativo(String versionSistemaOperativo) {
		this.versionSistemaOperativo = versionSistemaOperativo;
	}

	/**
	 * @return the versionDelNavegador
	 */
	public String getVersionDelNavegador() {
		return versionDelNavegador;
	}

	/**
	 * @param versionDelNavegador the versionDelNavegador to set
	 */
	public void setVersionDelNavegador(String versionDelNavegador) {
		this.versionDelNavegador = versionDelNavegador;
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
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
