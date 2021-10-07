package com.justmediagroup.ms.auditoriausuario.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.exception.TipoError;
import com.justmediagroup.modelo_canonico.Estado;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.IntentosLoginDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.TransaccionDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.ValidarUsuarioDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.ValorConfigurableDto;
import com.justmediagroup.ms.auditoriausuario.service.command.business.RegistrarValoresArchivoJsonCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.business.ValidarUsuarioLoginCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ActualizarIntentosLoginCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ActualizarTransaccionCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ActualizarUsuarioCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ConsultarIntentosLoginCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ConsultarTransaccionPorUsuarioIdyEnlace;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ConsultarUsuarioPorEmailCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ConsultarValorConfigurableCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.CrearIntentosLoginCommand;
import com.justmediagroup.ms.auditoriausuario.service.contract.IAuditoriaUsuarioSvc;

@Service
public class AuditoriaUsuarioSvcImpl implements IAuditoriaUsuarioSvc {

	@Autowired
	ConsultarUsuarioPorEmailCommand consultarUsuarioPorEmailCommand;

	@Autowired
	ConsultarTransaccionPorUsuarioIdyEnlace consultarTransaccionPorUrsuarioIdyEnlace;

	@Autowired
	ConsultarValorConfigurableCommand consultarValorConfigurableCommand;

	@Autowired
	ActualizarUsuarioCommand actualizarUsuarioCommand;

	@Autowired
	ActualizarTransaccionCommand actualizarTransaccionCommand;

	@Autowired
	ValidarUsuarioLoginCommand validarUsuarioLoginCmd;

	@Autowired
	ConsultarIntentosLoginCommand consultarIntentosLoginCommand;

	@Autowired
	CrearIntentosLoginCommand crearIntentosLoginCommand;

	@Autowired
	ActualizarIntentosLoginCommand actualizarIntentosLogin;

	@Autowired
	RegistrarValoresArchivoJsonCommand registrarValoresArchivoJsonCommand;

	@Override
	public Object verificarTransaccionUsuario(String enlace, String usuarioId, String sistema,
			String versionSistemaOperativo, String versionDelNavegador, String canal) throws BusinessException {

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AuditoriaUsuarioConstans.ENLACE, enlace)
				.addValue(AuditoriaUsuarioConstans.USUARIO_ID, usuarioId)
				.addValue(AuditoriaUsuarioConstans.SISTEMA, sistema)
				.addValue(AuditoriaUsuarioConstans.VERSION_OS, versionSistemaOperativo)
				.addValue(AuditoriaUsuarioConstans.VERSION_NAVEGADOR, versionDelNavegador)
				.addValue(AuditoriaUsuarioConstans.CANAL, canal).addValue(AuditoriaUsuarioConstans.CORREO, "N/A")
				.addValue(AuditoriaUsuarioConstans.PASSWORD, "N/A")
				.addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
						AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA);

		ValorConfigurableDto rutaArchivoJson = (ValorConfigurableDto) consultarValorConfigurableCommand
				.execute(stringParam);

		stringParam.addValue(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA,
				rutaArchivoJson.getValorDefecto());

		registrarValoresArchivoJsonCommand.execute(stringParam);

		TransaccionDto transaccionDto = new TransaccionDto();

		// 1.- CONSULTAMOS LA TRANSACCION DE ACCESO A LA PAGINA QUE DESEA ACCEDER EL USUARIO
		transaccionDto = (TransaccionDto) consultarTransaccionPorUrsuarioIdyEnlace.execute(stringParam);
		TransaccionDto transaccionAuditada = new TransaccionDto();
		transaccionAuditada = transaccionDto;
		transaccionAuditada.setCantidadVisitas(transaccionDto.getCantidadVisitas() + 1);

		stringParam.addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
				AuditoriaUsuarioConstans.VALOR_PERMITIDO_DE_ACCESO);

		// 2.- CONSULTAMOS EL VALOR PERMITIDO DE ACCESO (PARAMETRIZABLE)
		ValorConfigurableDto valorPermitidoAccesos = (ValorConfigurableDto) consultarValorConfigurableCommand
				.execute(stringParam);

		// 3.- ¿CANTIDAD DE VISITAS ES MENOR O IGUAL A VALOR PERMITIDO DE ACCESO (PARAMETRIZABLE)
		// ESTABLECIDAS AL USUARIO
		// ¿TIENE ACCESOS DISPONIBLE EL USUARIO?
		if (transaccionDto.getCantidadVisitas() <= Integer.valueOf(valorPermitidoAccesos.getValorDefecto())) {
			// ACTUALIZAMOS LA CANTIDAD DE VECES QUE HA QUERIDO ACCEDER EL USUARO
			transaccionAuditada.setPuedeAcceder(true);
			actualizarTransaccionCommand.execute(transaccionDto);
			return transaccionAuditada;
		} else {
			
		//  4.- SI TIENE ACCESOS LE ASIGNAMOS SUMAMOS UNA VISITA Y MANDAMOS A ACTUALIZAR LA TRANSACCION
			if (transaccionAuditada.isPuedeAcceder() != false) {
				transaccionAuditada.setPuedeAcceder(false);
				actualizarTransaccionCommand.execute(transaccionAuditada);
			}

			return transaccionDto;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioDto logearUsuario(String correo, String password, String sistema, String versionSistemaOperativo,
			String versionDelNavegador, String canal) throws BusinessException {

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AuditoriaUsuarioConstans.CORREO, correo)
				.addValue(AuditoriaUsuarioConstans.SISTEMA, sistema)
				.addValue(AuditoriaUsuarioConstans.PASSWORD, password)
				.addValue(AuditoriaUsuarioConstans.VERSION_OS, versionSistemaOperativo)
				.addValue(AuditoriaUsuarioConstans.VERSION_NAVEGADOR, versionDelNavegador)
				.addValue(AuditoriaUsuarioConstans.CANAL, canal).addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
						AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA);

		stringParam.addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
				AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA);

		ValorConfigurableDto rutaUbicacionArchivoJson = null;

		// 1.- CONSULTAMOS VALORES CONFIGURABLES (RUTA DE ARCHIVO JSON)
		rutaUbicacionArchivoJson = (ValorConfigurableDto) consultarValorConfigurableCommand.execute(stringParam);

		stringParam.addValue(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA,
				rutaUbicacionArchivoJson.getValorDefecto());

		registrarValoresArchivoJsonCommand.execute(stringParam);

		// 2.- CONSULTAR USUARIO POR EMAIL
		UsuarioDto usuario = (UsuarioDto) consultarUsuarioPorEmailCommand.execute(stringParam);

		// 3.- CONSULTAR INTENTOS LOGIN
		List<IntentosLoginDto> listadoIntentosLogin = null;
		listadoIntentosLogin = (List<IntentosLoginDto>) consultarIntentosLoginCommand.execute(stringParam);

		// 4.- ¿RETORNA USUARIO NULO?
		if (usuario == null) {

			stringParam.addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
					AuditoriaUsuarioConstans.MAXIMO_INTENTOS_LOGIN);

			// 5.- CONSULTAR VALORES CONFIGURABLES
			ValorConfigurableDto maximoIntentosLogin = (ValorConfigurableDto) consultarValorConfigurableCommand
					.execute(stringParam);

			// 6.- ¿CANTIDAD DE INTENTOS ES MENOR O IGUAL AL PARAMETRIZADO?
			if ((Integer.valueOf(maximoIntentosLogin.getValorDefecto())) >= listadoIntentosLogin.size()) {
				// POR VERDADERO REGISTRAMOS UN INTENTO DE LOGIN CON EL CORREO
				// ENVIADO POR LA URI DEL MICROSERVICIO
				IntentosLoginDto intentosLoginDTO = new IntentosLoginDto();
				intentosLoginDTO.setFechaCreacion(new Date());
				intentosLoginDTO.setEstado(Estado.ACTIVO);
				intentosLoginDTO.setCorreoUsuario(correo);

				// 7.- REGISTRAMOS EL INTENTO LOGIN
				crearIntentosLoginCommand.execute(intentosLoginDTO);

				// 8.- LANZAMOS EXCEPCION CREDENCIALES INCORRECTAS
				throw new BusinessException(AuditoriaUsuarioConstans.MENSAJE_CLAVE_INCORRECTA,
						TipoError.SOLICITUD_INVALIDA);

			} else {
				// 9.- POR FALSO ENTONCES LANZAMOS UNA EXCEPCION
				// DEBIDO A QUE EL USUARIO ESTA BLOQUEADO POR MAXIMO DE INTENTOS DE LOGIN
				throw new BusinessException(AuditoriaUsuarioConstans.MENSAJE_USUARIO_BLOQUEADO,
						TipoError.SOLICITUD_INVALIDA);
			}

		} else {
			ValidarUsuarioDto validarUsuarioDto = new ValidarUsuarioDto();
			validarUsuarioDto.setUsuario(usuario);

			// 10.- VALIDAMOS EL USUARIO
			validarUsuarioLoginCmd.execute(validarUsuarioDto);

			// 11.- VERIFICAMOS SI TUVO ALGUN INTENTO INCORRECTO DE LOGUEO
			if (listadoIntentosLogin.size() >= 1) {
				IntentosLoginDto intentosLogin = new IntentosLoginDto();
				intentosLogin.setIntentosLogin(listadoIntentosLogin);
				actualizarIntentosLogin.execute(intentosLogin);
			}

			return usuario;
		}
	}
}
