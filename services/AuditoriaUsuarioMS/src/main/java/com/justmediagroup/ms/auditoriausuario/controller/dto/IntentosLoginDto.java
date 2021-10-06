package com.justmediagroup.ms.auditoriausuario.controller.dto;

import java.util.List;

import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.modelo_canonico.IntentosLoginType;

public class IntentosLoginDto extends IntentosLoginType implements IParam {

	private List<IntentosLoginDto> intentosLogin;

	/**
	 * @return the intentosLogin
	 */
	public List<IntentosLoginDto> getIntentosLogin() {
		return intentosLogin;
	}

	/**
	 * @param intentosLogin the intentosLogin to set
	 */
	public void setIntentosLogin(List<IntentosLoginDto> intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

}
