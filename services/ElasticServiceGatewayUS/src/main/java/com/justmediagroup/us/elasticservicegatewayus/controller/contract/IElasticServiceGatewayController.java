package com.justmediagroup.us.elasticservicegatewayus.controller.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface IElasticServiceGatewayController {

	@Operation(method = "crearRegistroAuditoria", operationId = "crearRegistroAuditoria", description = "Capacidad que se encarga de crear datos de auditoria ", tags = "IlasticServiceGatewayServiceUSV1", summary = "crearRegistroAuditoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@PostMapping(value = "/api/us/auditoria/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "UsuarioType", required = true, content = @Content(schema = @Schema(implementation = DatosAuditoriaUsuario.class)))
	public ResponseEntity<Object> crearDatosAuditoriaUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody(required = true) DatosAuditoriaUsuario datosAuditoria);

}
