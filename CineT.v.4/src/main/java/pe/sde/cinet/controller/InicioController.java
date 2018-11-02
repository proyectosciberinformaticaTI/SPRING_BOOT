package pe.sde.cinet.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.sde.cinet.model.service.ITicketService;
import pe.sde.cinet.model.util.ConstantesUtil;

@Controller
@SessionAttributes("nameUser")
public class InicioController {

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private SessionRegistry sessionRegistry;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/iniciaSession")
	public String inicia(Model model) {
		model.addAttribute("usuarioValido", false);
		return "inicia";
	}

	@RequestMapping(value = "validaUser", method = RequestMethod.POST)
	public String guardarRole(Model model, @RequestParam(name = "username", required = false) String nameUser,
			RedirectAttributes flash, SessionStatus status) {

		final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

		for (final Object obj : allPrincipals) {
			UserDetails user = (UserDetails) obj;
			logger.info("usuN: " + nameUser + " UsuList" + user.getUsername());
			if (nameUser.equals(user.getUsername())) {
				flash.addFlashAttribute("info", "Usuario "+ nameUser +"ya ha iniciado sesión anteriormente");
				model.addAttribute("error","Usuario "+ nameUser +"ya ha iniciado sesión anteriormente");
				logger.error("Usuario "+ nameUser +"ya ha iniciado sesión anteriormente");
				return "inicia";
			}
		}
		model.addAttribute("nameUser", nameUser);
		model.addAttribute("usuarioValido", true);
		//return "login";
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
			model.addAttribute("error", "Ya ha iniciado sesión anteriormente!");
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("error",
					"Error en el login, Nombre de usuario o contraseña incorrecta, vuelva a intentarlo");
		}
		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con exito!");
			return "inicia";
		}
		return "login";
	}

	@GetMapping("/")
	public String inicio(Model model) {
		if (hasRole(ConstantesUtil.ROLE_CAJERO)) {
			return "redirect:/ticket/inicio";
		}
		if (hasRole(ConstantesUtil.ROLE_CONTROL)) {
			return "redirect:/ticket/form";
		}

		return "index";
	}

	@GetMapping("contador")
	public String getContador(Model model) {

		String[] totalTicketVU = { "0", "0" };

		try {
			totalTicketVU = ticketService.getTotalTicketVU();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error", e.getMessage());
		}
		model.addAttribute("totalV", totalTicketVU[0]);
		model.addAttribute("totalU", totalTicketVU[1]);
		return "contador";
	}

	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));

	}
}
