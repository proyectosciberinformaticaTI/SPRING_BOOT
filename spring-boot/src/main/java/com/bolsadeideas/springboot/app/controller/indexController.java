package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.service.IUsuarioService;

@Controller
public class indexController {

	@Value("${application.controllers.mensaje}")
	private String mensaje;
	
	
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	
	
	
@RequestMapping(value ="index",method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", mensaje);
		model.addAttribute("usuario", usuarioService.findAll());
		return "index";

	}

}
