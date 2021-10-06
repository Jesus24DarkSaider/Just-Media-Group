package com.justmediagroup.appweb.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.UserAgentParser;
import com.justmediagroup.appweb.constans.AppWebConstans;
import com.justmediagroup.appweb.dto.GenericStringParam;
import com.justmediagroup.appweb.model.Login;
import com.justmediagroup.appweb.service.contract.IRestClientSvc;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;

@Controller
public class AppWebController {

	private static final Logger LOG = LoggerFactory.getLogger(AppWebController.class);

	@Autowired
	private UserAgentParser userAgentParser;

	@Autowired
	private IRestClientSvc clienteServicioRest;

	UsuarioType user;

	String url = "";

	@GetMapping("/")
	public String index(Model modelo) {
		LOG.info("INICIANDO RAIZ");
		modelo.addAttribute("Login", new Login());
		return "index";
	}

	@PostMapping(value = "/menu")
	public String menuprincipal(@ModelAttribute Login cliente, @RequestHeader("User-Agent") String userAgent) {
		LOG.info(" " + cliente.getCorreo());
		LOG.info(" " + cliente.getContrasenia());

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();
		Capabilities capabilities = userAgentParser.parse(userAgent);
		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AppWebConstans.CORREO, cliente.getCorreo())
				.addValue(AppWebConstans.PASSWORD, cliente.getContrasenia())
				.addValue(AppWebConstans.SISTEMA, capabilities.getDeviceType() + capabilities.getPlatform())
				.addValue(AppWebConstans.VERSION_OS, capabilities.getPlatformVersion())
				.addValue(AppWebConstans.VERSION_NAVEGADOR,
						capabilities.getBrowser() + capabilities.getBrowserMajorVersion())
				.addValue(AppWebConstans.CANAL, "WEB");

		UsuarioType usuario;
		usuario = clienteServicioRest.logearUsuarioService(stringParam);
		user = usuario;

		if (usuario == null) {
			return "redirect:/";
		}

		return "principal";
	}

	@GetMapping("/pedidos")
	public String pedidos(Model modelo, @ModelAttribute Login cliente, @RequestHeader("User-Agent") String userAgent) {
		LOG.info("PEDIDOS");
		url = "/pedidos";

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();
		Capabilities capabilities = userAgentParser.parse(userAgent);

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AppWebConstans.ENLACE, Base64.getEncoder().encodeToString(url.getBytes()))
				.addValue(AppWebConstans.USUARIO_ID, user.getId().toString())
				.addValue(AppWebConstans.SISTEMA, capabilities.getDeviceType() + capabilities.getPlatform())
				.addValue(AppWebConstans.VERSION_OS, capabilities.getPlatformVersion())
				.addValue(AppWebConstans.VERSION_NAVEGADOR,
						capabilities.getBrowser() + capabilities.getBrowserMajorVersion())
				.addValue(AppWebConstans.CANAL, "WEB").addValue(AppWebConstans.CANAL, "N/A")
				.addValue(AppWebConstans.PASSWORD, "N/A");

		TransaccionesType transaccion;
		transaccion = clienteServicioRest.consultarTransaccion(stringParam);

		if (transaccion.isPuedeAcceder()) {
			return "pedidos";
		}

		return "notfount";
	}

	@GetMapping("/clientes")
	public String clientes(Model modelo, @ModelAttribute Login cliente, @RequestHeader("User-Agent") String userAgent) {
		LOG.info("CLIENTES");
		url = "/clientes";

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();
		Capabilities capabilities = userAgentParser.parse(userAgent);

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AppWebConstans.ENLACE, Base64.getEncoder().encodeToString(url.getBytes()))
				.addValue(AppWebConstans.USUARIO_ID, user.getId().toString())
				.addValue(AppWebConstans.SISTEMA, capabilities.getDeviceType() + capabilities.getPlatform())
				.addValue(AppWebConstans.VERSION_OS, capabilities.getPlatformVersion())
				.addValue(AppWebConstans.VERSION_NAVEGADOR,
						capabilities.getBrowser() + capabilities.getBrowserMajorVersion())
				.addValue(AppWebConstans.CANAL, "WEB").addValue(AppWebConstans.CANAL, "N/A")
				.addValue(AppWebConstans.PASSWORD, "N/A");

		TransaccionesType transaccion;
		transaccion = clienteServicioRest.consultarTransaccion(stringParam);

		if (transaccion.isPuedeAcceder()) {
			return "clientes";
		}

		return "notfount";
	}

	@GetMapping("/facturas")
	public String facturas(Model modelo, @ModelAttribute Login cliente, @RequestHeader("User-Agent") String userAgent) {
		LOG.info("FACTURAS");
		url = "/facturas";

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();
		Capabilities capabilities = userAgentParser.parse(userAgent);

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AppWebConstans.ENLACE, Base64.getEncoder().encodeToString(url.getBytes()))
				.addValue(AppWebConstans.USUARIO_ID, user.getId().toString())
				.addValue(AppWebConstans.SISTEMA, capabilities.getDeviceType() + capabilities.getPlatform())
				.addValue(AppWebConstans.VERSION_OS, capabilities.getPlatformVersion())
				.addValue(AppWebConstans.VERSION_NAVEGADOR,
						capabilities.getBrowser() + capabilities.getBrowserMajorVersion())
				.addValue(AppWebConstans.CANAL, "WEB").addValue(AppWebConstans.CANAL, "N/A")
				.addValue(AppWebConstans.PASSWORD, "N/A");

		TransaccionesType transaccion;
		transaccion = clienteServicioRest.consultarTransaccion(stringParam);

		if (transaccion.isPuedeAcceder()) {
			return "facturas";
		}

		return "notfount";
	}

	@GetMapping("/personal")
	public String string(Model modelo, @ModelAttribute Login cliente, @RequestHeader("User-Agent") String userAgent) {
		LOG.info("personal");
		url = "/personal";

		// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
		GenericStringParam stringParam = new GenericStringParam();
		Capabilities capabilities = userAgentParser.parse(userAgent);

		// VALORES A SER UTILIZADOS EN LA AUDITORIA
		stringParam.addValue(AppWebConstans.ENLACE, Base64.getEncoder().encodeToString(url.getBytes()))
				.addValue(AppWebConstans.USUARIO_ID, user.getId().toString())
				.addValue(AppWebConstans.SISTEMA, capabilities.getDeviceType() + capabilities.getPlatform())
				.addValue(AppWebConstans.VERSION_OS, capabilities.getPlatformVersion())
				.addValue(AppWebConstans.VERSION_NAVEGADOR,
						capabilities.getBrowser() + capabilities.getBrowserMajorVersion())
				.addValue(AppWebConstans.CANAL, "WEB").addValue(AppWebConstans.CANAL, "N/A")
				.addValue(AppWebConstans.PASSWORD, "N/A");

		TransaccionesType transaccion;
		transaccion = clienteServicioRest.consultarTransaccion(stringParam);

		if (transaccion.isPuedeAcceder()) {
			return "facturas";
		}

		return "notfount";
	}

}
