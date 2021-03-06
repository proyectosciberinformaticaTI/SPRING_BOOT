	package pe.egcc.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.egcc.app.dto.ClienteDto;
import pe.egcc.app.service.EurekaService;

@Controller
public class HomeController {

	@Autowired
	private EurekaService eurekaService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	@RequestMapping(value = "/procesarLogin", method = RequestMethod.POST)
	public String procesarLogin( String empleado, HttpSession session){
		session.setAttribute("empleado", empleado);
		
		return "home";
	}
	

	@RequestMapping(value = "/cerrarSesion", method = RequestMethod.GET)
	public String procesarLogin(HttpSession session){

		session.invalidate();
		return "home";
		
	}
	

	@RequestMapping(value = "/getSaldoCuenta", method = RequestMethod.GET)
	@ResponseBody
	public String getSaldoCuenta(String cuenta) {

		Double saldo = eurekaService.getSaldoCuenta(cuenta);

		return String.valueOf(saldo);

	}

	// =======================================
	// CONSULTAR DATOS DEL CLIENTE
	// =======================================
	
	@RequestMapping(value = "/getCliente", method = RequestMethod.GET)
	public String getCliente() {

		return "getCliente";

	}
	
	@RequestMapping(value = "/getCliente", method = RequestMethod.POST)
	public String getCliente(String codigo, Model model) {

		logger.info("Consultando.");
		
		ClienteDto rec = eurekaService.getCliente(codigo);
		model.addAttribute("rec", rec);
		
		return "getCliente";

	}

	@RequestMapping(value = "/traerCliente", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ClienteDto traerCliente(String codigo) {

		ClienteDto rec = eurekaService.getCliente(codigo);
		
		if(rec == null){
			rec = new ClienteDto();
			rec.setCodigo("0");
		}

		return rec;

	}
	
	
	// =======================================
	// CONSULTAR MOVIMIENTOS
	// =======================================
	@RequestMapping(value = "/getMovimientos", method = RequestMethod.GET)
	public String getMovimientos() {

		return "getMovimientos";

	}

	@RequestMapping(value = "/traerMovimientos", method = RequestMethod.GET, produces = "Application/json")
	@ResponseBody
	public List<Map<String, Object>> traerMovimientos(String cuenta) {

		List<Map<String, Object>> lista;
		lista = eurekaService.getMovimientos(cuenta);

		return lista;

	}

	// =======================================
	// PROCESAR DEPOSITO
	// =======================================

	@RequestMapping(value = "/procDeposito", method = RequestMethod.GET)
	public String procDeposito() {
		return "procDeposito";
	}

	@RequestMapping(value = "/procDeposito", method = RequestMethod.POST)
	public String procDeposito(
			@RequestParam("cuenta") String cuenta, 
			@RequestParam("importe") double importe,
			Model model) {
		try {
			String codEmp = "0003";
			eurekaService.procDeposito(cuenta, importe, codEmp);
			model.addAttribute("mensaje", "Proceso ejecutado correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "procDeposito";
	}
	
	// =======================================
	// PROCESAR RETIRO
	// =======================================

	@RequestMapping(value = "/procRetiro", method = RequestMethod.GET)
	public String procRetiro() {
		return "procRetiro";
	}

	@RequestMapping(value = "/procRetiro", method = RequestMethod.POST)
	public String procRetiro(
			@RequestParam("cuenta") String cuenta, 
			@RequestParam("importe") double importe,
			@RequestParam("clave") String clave,
			HttpSession session,
			Model model) {
		try {
			String codEmp = session.getAttribute("empleado").toString();
			eurekaService.procRetiro(cuenta, importe, codEmp, clave);
			model.addAttribute("mensaje", "Proceso ejecutado correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "procRetiro";
	}
	
	
	
	

}
