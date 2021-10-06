/**
 * 
 */
package com.justmediagroup.ms.auditoriausuario.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jesus
 *
 */

@Controller
public class HomeController {
	@RequestMapping("/")
	public String Index() {
		return "redirect:swagger-ui.html";
	}
}
