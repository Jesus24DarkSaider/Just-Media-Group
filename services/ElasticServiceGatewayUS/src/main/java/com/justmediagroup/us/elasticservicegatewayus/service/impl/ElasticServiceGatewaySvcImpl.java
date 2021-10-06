package com.justmediagroup.us.elasticservicegatewayus.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justmediagroup.us.elasticservicegatewayus.repository.contract.ElasticServiceGatewayRepository;
import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;
import com.justmediagroup.us.elasticservicegatewayus.service.contract.IElasticServiceGatewaySvc;

@Service
public class ElasticServiceGatewaySvcImpl implements IElasticServiceGatewaySvc {

	@Autowired
	ElasticServiceGatewayRepository elasticRepository;

	@Override
	public DatosAuditoriaUsuario crearDatosAuditoriaUsuario(@Valid DatosAuditoriaUsuario datosAuditoria) {
		// CREAMOS EL MODEL
		DatosAuditoriaUsuario model = new DatosAuditoriaUsuario();
		model.setEnlace(datosAuditoria.getEnlace());
		model.setUsuarioId(datosAuditoria.getUsuarioId());
		model.setSistema(datosAuditoria.getSistema());
		model.setVersionSistemaOperativo(datosAuditoria.getVersionSistemaOperativo());
		model.setVersionDelNavegador(datosAuditoria.getVersionDelNavegador());
		model.setCorreo(datosAuditoria.getCorreo());
		model.setPassword(datosAuditoria.getPassword());
		model.setCanal(datosAuditoria.getCanal());
		return elasticRepository.save(model);
	}

}
