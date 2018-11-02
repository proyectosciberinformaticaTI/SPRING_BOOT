package pe.sde.cinet.model.entity;

import java.io.Serializable;

public class ReporteBean implements Serializable {	
	private static final long serialVersionUID = 1L;
	private String ano;
	private String mes;
	private String dia;
	private Integer totalTikets;
	private Double totalIngresos;
	

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Integer getTotalTikets() {
		return totalTikets;
	}
	public void setTotalTikets(Integer totalTikets) {
		this.totalTikets = totalTikets;
	}
	public Double getTotalIngresos() {
		return totalIngresos;
	}
	public void setTotalIngresos(Double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	
}
