/**
 * 
 */
package com.justmediagroup.ms.auditoriausuario.service.contract;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;

/**
 * @author jesus
 *
 */
public interface IAuditoriaUsuarioSvc {

	public Object verificarTransaccionUsuario(String enlace, String usuarioId, String sistema,
			String versionSistemaOperativo, String versionDelNavegador, String canal) throws BusinessException;

	public UsuarioDto logearUsuario(String correo, String password, String sistema, String versionSistemaOperativo,
			String versionDelNavegador, String canal) throws BusinessException;
}
