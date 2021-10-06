/**
 * 
 */
package com.justmediagroup.es.usuario.repository.contract;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.justmediagroup.es.usuario.repository.model.ValoresConfigurables;


/**
 * @author jesus
 *
 */
@Repository
public interface IValoresConfigurablesRepository extends JpaRepository<ValoresConfigurables, UUID> {

	@Query(nativeQuery = true, value = "select * from  tb_valores_configurables "
			+ "tvc where tvc.nombre  = :nombre ")
	public ValoresConfigurables consultarValorConfigurablePorNombre(@Param("nombre")String nombre );

	
}
