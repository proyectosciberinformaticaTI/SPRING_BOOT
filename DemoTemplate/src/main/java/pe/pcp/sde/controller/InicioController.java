package pe.pcp.sde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
	
	@RequestMapping({"/","index"})
	public String inicio(){
		return "index";
	}
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("register")
	public String register(){
		return "register";
	}
		
	@RequestMapping("forgotPassword")
	public String forgotPassword(){
		return "forgot-password";
	}
	

	

	
	@RequestMapping("configuracionClienteProducto")
	public String configuracionClienteProducto() {
		return "comercial/configuracionClienteProducto";
		
	}

	




	
	
	


	

	

	
	
	
	
	
}
