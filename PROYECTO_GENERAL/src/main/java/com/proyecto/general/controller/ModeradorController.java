package com.proyecto.general.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.proyecto.general.bean.agraviadosBean;
import com.proyecto.general.bean.moderadorBean;
import com.proyecto.general.service.agraviadosService;
import com.proyecto.general.service.moderadorService;

@Controller
public class ModeradorController {

	@Autowired
	private moderadorService logonServicesmoderador;
	
	@Autowired
	private agraviadosService agraviadosServicelis;
	
	@RequestMapping(value = "menuAdministrador", method = RequestMethod.GET)
	  public String menuPrincipal() {
	    return "menuAdministrador";
	  }
	@RequestMapping(value = "mantenimientoAgraviados", method = RequestMethod.GET)
	  public String registrarMantenimientoAgraviados() {
	    return "mantenimientoAgraviados";
	  }
	
	@RequestMapping(value = "mantenimientoModerador", method = RequestMethod.GET)
	  public String registrarMantenimientoModeradores() {
	    return "mantenimientoModerador";
	  }
	
	
	@RequestMapping(value="loginModeradores", method=RequestMethod.POST)
	public ModelAndView autenticarUsuarioModerador(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("principalModerador");
		HttpSession session = request.getSession();

		try {
		      
		      String usuCodigo = request.getParameter("usuCodigo");
		      String usuPassword = request.getParameter("usuPassword");
		      
		      moderadorBean bean;
		      bean = logonServicesmoderador.login(usuCodigo,usuPassword);

		      session.setAttribute("UsuarioLogons", usuCodigo);
		      view.setViewName("menuAdministrador");
		      
		    } catch (Exception e) {
		      
		      view.addObject("error", e.getMessage());
		      view.setViewName("loginAdministrador");
		    }
		
		
		return view;
	}
	

	
	
	
	
	
	@RequestMapping(value = "/traerAgraviados", 
			method = RequestMethod.GET,produces = "Application/json")
	@ResponseBody
	public List<agraviadosBean> traerClientes
	(String dni,Model model ) {

		List<agraviadosBean> lista;
		lista = agraviadosServicelis.traerPorNombre(dni);
		model.addAttribute("lista", lista);
		return lista;

	}
	
	
	
	
	@RequestMapping(value = "/traerModeradores", 
			method = RequestMethod.GET,produces = "Application/json")
	@ResponseBody
	public List<moderadorBean> traerModeradores
	(String dni,Model model ) {

		List<moderadorBean> lista;
		lista = logonServicesmoderador.traerPorNombre(dni);
		
		return lista;

	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/registrarModerador", method = RequestMethod.POST)
	public String registrarMantenimientoModerador(@ModelAttribute moderadorBean moderador, Model model) {
	
		try {
		
			logonServicesmoderador.insertar(moderador);
			  model.addAttribute("mensaje", "Registrado Correctamente. ");
		 
		    } catch (Exception e) {
		      model.addAttribute("mensaje", e.getMessage());
		    }
		    return "mantenimientoModerador";
	
	}	
	
	
	
	@RequestMapping(value = "/cerrarSesionModerador", method = RequestMethod.GET)
	public String procesarLogin(HttpSession session){

		session.invalidate();
		return "menuPrincipal";
		
	}
	
	
}
