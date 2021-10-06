package com.justmediagroup.us.elasticservicegatewayus.repository.contract;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;

@Repository
public interface ElasticServiceGatewayRepository extends ElasticsearchRepository<DatosAuditoriaUsuario, String> {

}
