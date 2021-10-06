/**
 * 
 */
package com.justmediagroup.logica_comun.patrones.command;

import com.justmediagroup.logica_comun.exception.BusinessException;


public interface ICommand {

	Object execute(IParam parametro) throws BusinessException;
	
	void undo();

}
