package com.proyecto.general.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.general.bean.agraviadosBean;
import com.proyecto.general.bean.moderadorBean;
import com.proyecto.general.service.agraviadosService;
import com.proyecto.general.service.moderadorService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private agraviadosService logonService;

		
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
	
	
	@RequestMapping(value = "/registrarReporte", method = RequestMethod.GET)
	public String registrarReporte(Model model) {
	
		return "registrarReporte";
	
	}	
	
	
	@RequestMapping(value = "/registrarAgraviado", method = RequestMethod.POST)
	public String registrarMantenimientoAgraviado(@ModelAttribute agraviadosBean agraviadoBean, Model model) {
	
		try {
		
			logonService.insertar(agraviadoBean);
			  model.addAttribute("mensaje", "Usuario Registrado Correctamente. ");
		      model.addAttribute("usuario", agraviadoBean.getUSUARIO()); 
		      model.addAttribute("clave", agraviadoBean.getCLAVE());

		    } catch (Exception e) {
		      model.addAttribute("mensaje", e.getMessage());
		    }
		    return "index";
	
	}	
	
	@RequestMapping(value = "registrarAgraviado", method = RequestMethod.GET)
	  public String registrarMantenimientoAgraviado() {
	    return "n";
	  }

	
	
	
	
	
	


	@RequestMapping(value="loginUsuario", method=RequestMethod.POST)
	public ModelAndView autenticarUsuario(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("principal");
		HttpSession session = request.getSession();

		try {
		      
		      String usuCodigo = request.getParameter("usuCodigo");
		      String usuPassword = request.getParameter("usuPassword");
		      
		      agraviadosBean bean;
		      bean = logonService.login(usuCodigo,usuPassword);

		      session.setAttribute("UsuarioLogon", bean);
		      view.setViewName("registrarReporte");
		      
		    } catch (Exception e) {
		      
		      view.addObject("error", e.getMessage());
		      view.setViewName("index");
		    }
		
		
		return view;
	}
	@RequestMapping(value = "/cerrarSesionAgraviado", method = RequestMethod.GET)
	public String procesarLogin(HttpSession session){

		session.invalidate();
		return "menuPrincipal";
		
	}
	
//	@Autowired
//	private moderadorService logonServices;
//	
//	
//	
//	
//	
//	@RequestMapping(value="loginModeradores", method=RequestMethod.POST)
//	public ModelAndView autenticarlosmoderadores(HttpServletRequest request){
//		
//		ModelAndView view = new ModelAndView("principal");
//		HttpSession session = request.getSession();
//
//		try {
//		      
//		      String usuCodigo = request.getParameter("usuCodigo");
//		      String usuPassword = request.getParameter("usuPassword");
//		      
//		      moderadorBean bean;
//		      bean = logonServices.login(usuCodigo,usuPassword);
//
//		      session.setAttribute("UsuarioLogons", bean);
//		      view.setViewName("menuAdmin");
//		      
//		    } catch (Exception e) {
//		      
//		      view.addObject("error", e.getMessage());
//		      view.setViewName("loginAdministrador");
//		    }
//		
//		
//		return view;
//	}
//	
//	
	
	
	
}
