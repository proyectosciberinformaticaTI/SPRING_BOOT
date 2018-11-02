package pe.sde.cinet.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sourceforge.jbarcodebean.BarcodeException;
import pe.sde.cinet.model.entity.ReporteBean;
import pe.sde.cinet.model.entity.ReporteParcialBean;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Ticket;
import pe.sde.cinet.model.entity.Usuario;
import pe.sde.cinet.model.service.ITicketService;
import pe.sde.cinet.model.util.ConstantesUtil;

@Controller
@RequestMapping("/ticket")
public class TicktController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ITicketService ticketService;
	
	@Secured({"ROLE_CAJERO"})
	@GetMapping("inicio")
	public String listar(Model model, HttpServletRequest request) {
		// ObtieneUsuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		List<Ticket> lisTicket = null;
		ReporteParcialBean reporte =null;
		Tarifa tarifa = null;
		if(request.isUserInRole(ConstantesUtil.ROLE_ADMIN) || request.isUserInRole(ConstantesUtil.ROLE_SUPERVISOR)){
			lisTicket =  ticketService.findToDayAll();
		}else{
			lisTicket =  ticketService.findToDayForUser(usuario);
		}
		
		try {
			reporte = ticketService.generarReporteCorte(usuario);
			tarifa = ticketService.getTarifa();
		} catch (Exception e) {
			logger.error("error",  e.getMessage());
			e.printStackTrace();
		}
		List<Usuario> lisCajeros = ticketService.findAllUserByRole();
		
		model.addAttribute("titulo", "Generacion de Tickets");
		model.addAttribute("lisTicket", lisTicket);
		model.addAttribute("lisCajeros", lisCajeros);
		model.addAttribute("reporte", reporte);
		model.addAttribute("tarifa", tarifa);
		model.addAttribute("byteArrayCodBarra", "");
		return "ticket/generar";
	}
	
	@Secured({"ROLE_CAJERO"})
	@RequestMapping(value="inicioUser", method= RequestMethod.POST)
	public String guardarRole(Model model,
			@RequestParam(name="userSelect", required = false) String userSelect,			
			RedirectAttributes flash, SessionStatus status){		
		
				List<Ticket> lisTicket = null;
				
				lisTicket =  ticketService.findToDayForIdUser(userSelect);
				
				
				List<Usuario> lisCajeros = ticketService.findAllUserByRole();
				
				model.addAttribute("titulo", "Generacion de Tickets");
				model.addAttribute("lisTicket", lisTicket);
				model.addAttribute("lisCajeros", lisCajeros);
				model.addAttribute("byteArrayCodBarra", "");
				return "ticket/generar";
	}
	
	
	
	@GetMapping("reporte")
	public String verReporte(Model model, RedirectAttributes flash){
		List<ReporteBean> lreporte = null;		
		try {
			lreporte = ticketService.generarReporte();
		} catch (Exception e) {
			e.printStackTrace();
			flash.addFlashAttribute("error", "No se pudo generar Reporte, detalle: " + e.getMessage());
		}
		model.addAttribute("titulo", "Reporte Ventas");
		model.addAttribute("lisReporte", lreporte);
		return "ticket/reporte";
	}
	
	@GetMapping("reporteParcial")
	public String verReporteParcial(Model model, RedirectAttributes flash){
		ReporteParcialBean reporte = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		try {
			reporte = ticketService.generarReporteCorte(usuario);
		} catch (Exception e) {
			logger.error("error",  e.getMessage());
			e.printStackTrace();
			flash.addFlashAttribute("error", "No se pudo generar Reporte, detalle: " + e.getMessage());
		}
		model.addAttribute("Titulo", "Reporte Corte del Día");
		model.addAttribute("reporte", reporte);
		return "ticket/reporteCorte";
	}

	@GetMapping("registra")
	public String registraCodigo(Model model, RedirectAttributes flash) {
		// ObtieneUsuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		Map<String, Object> ticket = null;
		try {
			ticket = ticketService.generaTicket(usuario);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error",  e.getMessage());
		} catch (BarcodeException e) {
			logger.error("error",  e.getMessage());
			e.printStackTrace();
		}
		
		String titulo = ((Ticket) ticket.get("ticket")).toString();
		model.addAttribute("titulo", titulo);
		model.addAttribute("ticket", (Ticket) ticket.get("ticket"));
		model.addAttribute("bufTicket", (BufferedImage) ticket.get("bufTicket"));
		model.addAttribute("byteArrayCodBarra", ticket.get("b64"));

		return "ticket/ver";
	}
	
	@Secured({"ROLE_CAJERO"})
	@RequestMapping(value="cancela/{codBarra}")
	public String cancelaTicket(Model model, RedirectAttributes flash,
								@PathVariable(value="codBarra") String codBarra) {
		ticketService.cancelaTicket(codBarra);
		return "redirect:/ticket/inicio";
	}
	
	@GetMapping("genera")
	public String generaTicket(Model model, RedirectAttributes flash) {
		// ObtieneUsuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuario = auth.getName();
		Map<String, Object> ticket = null;
		String contadorTicket = "1";
		try {
			contadorTicket = ticketService.cuentaTickets();
			ticket = ticketService.generaTicket(usuario);
			
		} catch (IOException e) {
			logger.error("error",  e.getMessage());
			e.printStackTrace();
		} catch (BarcodeException e) {
			e.printStackTrace();
			logger.error("error",  e.getMessage());
		} catch (Exception e) {			
			e.printStackTrace();
			logger.error("error",  e.getMessage());
		}		
		String titulo = ((Ticket) ticket.get("ticket")).toString();
		model.addAttribute("titulo", titulo);
		model.addAttribute("ticket", (Ticket) ticket.get("ticket"));
		model.addAttribute("bufTicket", (BufferedImage) ticket.get("bufTicket"));
		model.addAttribute("byteArrayCodBarra", ticket.get("b64"));
		
		model.addAttribute("titulo", "Generacion de Tickets");
		List<Ticket> lisTicket =  ticketService.findToDayForUser(usuario);
		model.addAttribute("lisTicket", lisTicket);
		model.addAttribute("contadorTicket", contadorTicket);
		 return "redirect:/ticket/inicio";
	}

	@Secured({"ROLE_CONTROL"})
	@RequestMapping(value = "form")
	public String vistaValida(Model model) {
		model.addAttribute("titulo", "Validación de Ticket");
		return "ticket/valida";
	}
	
	@Secured({"ROLE_CONTROL"})
	@PostMapping("valida")
	public String validaCodigo(@RequestParam(name = "codBarra", required = true) String codBarra, Model model,
			RedirectAttributes flash) {

		Map<String, Object> ticket = ticketService.findBycodigoBarra(codBarra);

		if((boolean) ticket.get("ok")){
			flash.addFlashAttribute("success", "TICKET: " + codBarra + " - " + ticket.get("detalle"));
		}else{
			flash.addFlashAttribute("error", "TICKET: " + codBarra + " - " + ticket.get("detalle"));
		}
		
		model.addAttribute("ok", (boolean) ticket.get("ok") );
		model.addAttribute("detalle",  "Ticket:" + codBarra + " " + ticket.get("detalle"));
		model.addAttribute("titulo", "Validación de Ticket");
		return "ticket/valida";
	}
}
