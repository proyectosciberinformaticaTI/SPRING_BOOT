package pe.pcp.sde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pcp")
public class PcpController {

	
	
	
	@RequestMapping("detallesReportes")
	public String detallesReportes() {
		
		
		return "pcp/detallesReportes";
	}
	
	
	
	@RequestMapping("totaldeCajas")
	public String totaldeCajas() {
		
		
		return "pcp/totaldeCajas";
	}
	
	
	@RequestMapping("productoStocksinProyeccion")
	public String productoStocksinProyeccion() {
		
		return "pcp/productoStocksinProyeccion";
	}

	
	
	@RequestMapping("cumplimiento")
	public String cumplimiento() {
		
		return "pcp/cumplimiento";
	}
	
	
	
	
	
	@RequestMapping("consumoMateriales")
	public String consumoMateriales() {
		
		return "pcp/consumoMateriales";
	}
	
	
	@RequestMapping("analisisCapacidadesProductoNuevo")
	public String analisisCapacidadesProductoNuevo() {
		
		
		return "pcp/analisisCapacidadesProductoNuevo";
		
	}
	
	
	
}
