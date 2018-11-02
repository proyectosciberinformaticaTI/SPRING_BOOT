package pe.pcp.sde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comercial")
@Controller
public class ComercialController {
	
	
	
	


	
	@RequestMapping("proyeccion")
	public String proyeccion(){
		return "comercial/registroProyeccion";
	}
	

	@RequestMapping("repAvances")
	public String repAvances(){
		return "comercial/repAvances";
	}
	
	
	@RequestMapping("reporteAvance")
	public String reporteAvance() {
		return "comercial/reporteAvance";
		
	}
	
	
	
	
	@RequestMapping("reporteAvanceDetalleSKU")
	public String reporteAvanceDetalleSKU() {
		return "comercial/reporteAvanceDetalleSKU";
		
	}
	

	
	@RequestMapping("carUnitaria")
	public String carUnitaria(){
		return "comercial/inicioUnitario";
	}
	
	@RequestMapping("carMasiva")
	public String carMasiva(){
		return "comercial/inicioMasivo";
	}
	

	@RequestMapping("carHistorica")
	public String carHistorica(){
		return "comercial/inicioCargaHistoricos";
	}
	
	
	
	
	
	
	
	@RequestMapping("configuracionCliente_Producto")
	public String configuracionCliente_Producto() {
		return "comercial/configuracionCliente_Producto";
		
	}
	
	
	
	
	@RequestMapping("carEditarProyeccion")
	public String editarIndividual(){
		return "comercial/editarProyeccion";
	}
	
	
	

	
	@RequestMapping("configuracionClienteProductoMasivo")
	public String confClienteProductoMasivo() {
		return "comercial/configuracionClienteProductoMasivo";
	}
	
	
	
	@RequestMapping("listarProductosClientePlanta")
	public String carlistarProductosClientePlanta() {
		return "comercial/listarProductosClientePlanta";
		
	}
	
	
	@RequestMapping("carEditarUnitaria")
	public String editarUnitarios() {
		return "comercial/editarProyeccion";
		
	}
	
	
	
	@RequestMapping("resumenCarga")
	public String cargaResumen() {
		
		return "comercial/resumenCarga";
		
		
	}
	
	
	
	


	
	

	@RequestMapping("listarProductosClientePlantaHistorico")
	public String carlistarProductosClientePlantahistorico() {
		return "comercial/listarProductosClientePlantaHistorico";
		
	}
	
	

	

	
	@RequestMapping("carEditarMasiva")
	public String editarMasiva() {
		return "comercial/editarMasiva";
		
	}
	
	
	
	@RequestMapping("carEditarHistorico")
	public String carEditarHistorico() {
		
		
		return "comercial/editarHistorico";
	}
	
	
	


}
