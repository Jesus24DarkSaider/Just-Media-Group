package com.justmediagroup.es.usuario.repository.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.justmediagroup.es.usuario.repository.model.IntentosLogin;

public interface IIntentosLoginRepository extends JpaRepository<IntentosLogin, UUID> {

	@Query(nativeQuery = true, value = "select * from intentos_login il "
			+ "where il.correo_usuario = :correoUsuario and il.estado  = 'ACTIVO' ;")
	public List<IntentosLogin> consultarIntentosLoginPorCorreo(String correoUsuario);

}
