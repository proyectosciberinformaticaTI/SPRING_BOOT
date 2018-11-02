package pe.sde.cinet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.sde.cinet.model.entity.PrintConfiguration;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Usuario;
import pe.sde.cinet.model.service.IMantenimientoService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/mantenimiento")
@SessionAttributes({"usuario", "printConfig","tarifa"})
public class MantenimientoController {

	@Autowired
	private IMantenimientoService mantenimientoService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//MANTENIMINETO USUARIO:
	@RequestMapping(value = "usuarios", method = RequestMethod.GET)
	public String listarUsuario(Model model) {		
		List<Usuario> lisUsuario = mantenimientoService.findAllUser();		
		model.addAttribute("titulo", "Mantenimiento de Usuarios");
		model.addAttribute("lisUsuario", lisUsuario);
		return "mantenimiento/userList";
	}
	
	@RequestMapping(value="formUsuario")
	public String crear(Map<String, Object> model){		
		Usuario usuario = new Usuario();		
		model.put("usuario", usuario);
		model.put("titulo", "Crear Usuario");		
		return "mantenimiento/userForm";
	}
	
	
	
	@RequestMapping(value="formUsuario/{id}")
	public String editar(@PathVariable(value="id") Long id , Map<String, Object> model,RedirectAttributes flash){
		Usuario usuario = null;	
		if(id > 0) {
			usuario = mantenimientoService.findUserById(id);
			
			if(usuario == null ) {
				flash.addFlashAttribute("error", "El ID del Usuario no existe en la BBDD!");
				return "redirect:/mantenimiento/usuarios";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del Usuario no puede ser cero!");
			return "redirect:/mantenimiento/usuarios";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		return "mantenimiento/userForm";
	}
	
	@RequestMapping(value="permisosUsuario/{id}")
	public String editarPermisos(@PathVariable(value="id") Long id , Map<String, Object> model,RedirectAttributes flash){
		Usuario usuario = null;	
		if(id > 0) {
			usuario = mantenimientoService.findUserById(id);
			
			if(usuario == null ) {
				flash.addFlashAttribute("error", "El ID del Usuario no existe en la BBDD!");
				return "redirect:/mantenimiento/usuarios";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del Usuario no puede ser cero!");
			return "redirect:/mantenimiento/usuarios";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar Permisos Usuario");
		return "mantenimiento/userAccesos";
	}
	
	@RequestMapping(value="eliminaRol/{idUsuario}/{idRole}")
	public String eliminarRole(@PathVariable(value="idUsuario") Long idUsuario, 
			@PathVariable(value="idRole") Long idRole , 
			Map<String, Object> model,RedirectAttributes flash){
		if(idRole > 0 && idUsuario > 0) {
			mantenimientoService.deleteRoleById(idRole);
		} else {
			flash.addFlashAttribute("error", "El ID del Role no puede ser cero!");
		}
		model.put("titulo", "Editar Permisos Usuario");
		return "redirect:/mantenimiento/permisosUsuario/"+idUsuario;
	}
	
	@RequestMapping(value="disabledUser/{idUsuario}")
	public String disabledUser(@PathVariable(value="idUsuario") Long idUsuario, 
			Map<String, Object> model,RedirectAttributes flash){
		if(idUsuario > 0) {
			mantenimientoService.disabledUser(idUsuario);
		} else {
			flash.addFlashAttribute("error", "Usaurio no válido!");
		}
		model.put("titulo", "Editar Permisos Usuario");
		return "redirect:/mantenimiento/usuarios";
	}
	
	@RequestMapping(value="usuarioAddRol", method= RequestMethod.POST)
	public String guardarRole(Model model,
			@RequestParam(name="idUsuario", required = false) String idUsuario,
			@RequestParam(name="rolSelect", required = false) String rol,
			RedirectAttributes flash, SessionStatus status){		
		try {			
			mantenimientoService.saveRole(idUsuario, rol);
		} catch (Exception e) {
			logger.error("error", "Error al registrar usuario>>>> " + e.getMessage());
			model.addAttribute("titulo", "Permisos Usuario");
			model.addAttribute("error", "Error al registrar Roll>>>> " + e.getMessage());
			return "redirect:/mantenimiento/permisosUsuario/" + idUsuario;
		}
		status.setComplete();
		flash.addFlashAttribute("success", "Acceso agregado con éxito.");
		return "redirect:/mantenimiento/permisosUsuario/" + idUsuario;
	}
	
	
	
	
	@RequestMapping(value="formUsuario", method= RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model,			
			RedirectAttributes flash, SessionStatus status){
		if(result.hasErrors()){
			logger.info("form con errores ");
			model.addAttribute("titulo", "Formulario de Usuario");
			return "mantenimiento/userForm";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userNameReg = auth.getName();		
		String mensajeFlash = (usuario.getId() != null)?"Usuario Editado con éxito.":"Usuario registrado con éxito.";
		
		try {
			mantenimientoService.saveUsuario(usuario, userNameReg);
		} catch (Exception e) {
			logger.error("error", "Error al registrar usuario>>>> " + e.getMessage());
			model.addAttribute("titulo", "Formulario Usuario");
			model.addAttribute("error", "Error al registrar usuario>>>> " + e.getMessage());
			return "mantenimiento/userForm";
		}
				
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/mantenimiento/usuarios";
	}
///////////////////////////////////////////////////////////////////////
/********************USUARIOS CONECTADOS***************************/

	@GetMapping("usuariosConectados")
	public String usuariosEnLinea(Model model){
		final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		System.out.println("Principals: " + allPrincipals.size());
		List<Usuario> lisUsuarios = new ArrayList<>();
        for(final Object principal : allPrincipals) {
        	UserDetails user = (UserDetails) principal;
        	System.out.println("ser: " + user.getUsername());
        	lisUsuarios.add(mantenimientoService.findUserByUserName(user.getUsername()));
        }
		
		model.addAttribute("lisUsuarios", lisUsuarios);
		
		return "mantenimiento/userConected";
	}
	
///////////////////////////////////////////////////////////////////////
/*********************TARIFA*************************/
	@RequestMapping(value = "tarifas", method = RequestMethod.GET)
	public String listarTarifas(Model model) {		
		List<Tarifa> lisTarifas = mantenimientoService.findAllTarifas();		
		model.addAttribute("titulo", "Mantenimiento de Usuarios");
		model.addAttribute("lisTarifas", lisTarifas);
		return "mantenimiento/tarifaList";
	}
	
	@RequestMapping(value="formTarifa")
	public String crearTarifa(Map<String, Object> model){
		Tarifa tarifa = new Tarifa();
		model.put("tarifa", tarifa);
		model.put("titulo", "Crear Tarifa");
		return "mantenimiento/tarifaForm";
	}

	@RequestMapping(value="cambiaTarifa/{idTarifa}")
	public String cambioTarifa(@PathVariable(value="idTarifa") Long idTarifa, 
			Map<String, Object> model,RedirectAttributes flash){
		if(idTarifa > 0) {
			mantenimientoService.activarTarifa(idTarifa);
		} else {
			flash.addFlashAttribute("error", "El Tarifa no valida!");
		}
		model.put("titulo", "Cambio de Tarifa");
		return "redirect:/mantenimiento/tarifas";
	}
	
	@RequestMapping(value="formTarifa", method= RequestMethod.POST)
	public String guardaTarifa(@Valid Tarifa tarifa,
								BindingResult result, Model model,			
								RedirectAttributes flash,
								SessionStatus status){
		if(result.hasErrors()){
			logger.info("form con errores ");
			model.addAttribute("titulo", "Formulario de Tarifa");
			return "mantenimiento/tarifaForm";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		
		try {
			
			mantenimientoService.saveTarifa(tarifa, usuario);
		} catch (Exception e) {
			logger.error("error", "Error al registrar Tarifa>>>> " + e.getMessage());
			model.addAttribute("titulo", "Formulario Usuario");
			model.addAttribute("error", "Error al registrar Tarifa>>>> " + e.getMessage());
			return "mantenimiento/tarifaForm";
		}
				
		status.setComplete();
		flash.addFlashAttribute("success", "Tarifa registrada satisfactoriamente");
		return "redirect:/mantenimiento/tarifas";
	}

	///////////////////////////////////////////////////////////////////////
	/***********************PRINT CONFIGURATION***********************/
	
	@GetMapping("formConfPrint")
	public String configurePrint(Model model){
		// ObtieneUsuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		PrintConfiguration printConfig=null;
		try {
			printConfig = mantenimientoService.getPrintConfigByUser(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("titulo", "Configuración de Impresora");
		model.addAttribute("printConfig", printConfig);
		return "mantenimiento/printConfig";
	}
	
	@RequestMapping(value="formConfPrint", method= RequestMethod.POST)
	public String configurePrint(Model model,
			@ModelAttribute("printConfig") @Valid PrintConfiguration printConfig,
			BindingResult result,
			RedirectAttributes flash,
			SessionStatus status
			){
		
		if(result.hasErrors()){
			model.addAttribute("titulo", "Formulario de Cliente");
			return "mantenimiento/printConfig";			
		}
		
		try {			
			mantenimientoService.savePrintConfiguration(printConfig);
		} catch (Exception e) {
			logger.error("error", e);
			model.addAttribute("titulo", "Permisos Usuario");
			model.addAttribute("error", "Error al registrar Roll>>>> " + e.getMessage());
			return "mantenimiento/printConfig";
		}
		status.setComplete();
		flash.addFlashAttribute("success", "Configuracion aplicada con éxito.");
		return "redirect:/";
	}
	
}
