package com.proyecto.general;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proyecto.bean.agraviadosBean;
import com.proyecto.service.agraviadosService;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private agraviadosService clienteService;
		
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
	
		return "menuPrincipal";
	
	}
	@RequestMapping(value = "/loginModerador", method = RequestMethod.GET)
	public String loginModerador(Model model) {
	
		return "loginModerador";
	
	}	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginAgraviado(Model model) {
	
		return "index";
	
	}	
	
	@RequestMapping(value = "/loginAdministrador", method = RequestMethod.GET)
	public String loginAdministrador(Model model) {
	
		return "loginAdministrador";
	
	}	
	
	
	
	


	@RequestMapping(value = "/loginUsuario", method = RequestMethod.GET)
	public String login(HttpServletRequest  request, Model model) {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("clave");
		agraviadosBean cliente = clienteService.login(usuario,password);
		if(cliente != null){
			request.getSession().setAttribute("cliente", cliente);
			String nombresCompletos = cliente.getNOMBRE_APELLIDO() + " " + cliente.getDNI() + " " + cliente.getDIRECCION();
			model.addAttribute("mensaje","Bienvenido al sistema " + nombresCompletos.toString());
			model.addAttribute("tipo_mensaje","alert alert-success");
			return "menu";
		}else{
			model.addAttribute("mensaje","El usuario ingresado no existe en el sistema");
			model.addAttribute("tipo_mensaje","alert alert-danger");
			return "index";
		}
		
	}
	
	
	
	
	
}
