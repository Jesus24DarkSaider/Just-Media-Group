package com.justmediagroup.logica_comun.patrones.command;

import java.util.Deque;
import java.util.LinkedList;

import com.justmediagroup.logica_comun.exception.BusinessException;

public abstract class Invocador {
	private Deque<ICommand> pilaDesahacer = new LinkedList<ICommand>();
	
	public Object ejecutarComando(ICommand comando, IParam parametro)  throws BusinessException {
		Object resultado = comando.execute(parametro);
		pilaDesahacer.offerLast(comando);
		return resultado;
	}
	
	public void dehacerUltimoComando(IParam parametro) {
		if( !pilaDesahacer.isEmpty()) {
			ICommand comando = pilaDesahacer.pollLast();
			comando.undo();
		}
		
	}
}
