package pe.sde.cinet.model.entity;

import java.io.Serializable;

public class ReporteParcialBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Establecimiento local;
	private Usuario usuario;
	private Integer totalTickets;
	private Integer totalCancelados;
	private Integer totalValidos;
	private Double totalIngresos;
	private String fechaHoraGeneracion;
	public Establecimiento getLocal() {
		return local;
	}
	public void setLocal(Establecimiento local) {
		this.local = local;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(Integer totalTickets) {
		this.totalTickets = totalTickets;
	}
	public Integer getTotalCancelados() {
		return totalCancelados;
	}
	public void setTotalCancelados(Integer totalCancelados) {
		this.totalCancelados = totalCancelados;
	}
	public Double getTotalIngresos() {
		return totalIngresos;
	}
	public void setTotalIngresos(Double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	public String getFechaHoraGeneracion() {
		return fechaHoraGeneracion;
	}
	public void setFechaHoraGeneracion(String fechaHoraGeneración) {
		this.fechaHoraGeneracion = fechaHoraGeneración;
	}
	public Integer getTotalValidos() {
		return totalValidos;
	}
	public void setTotalValidos(Integer totalValidos) {
		this.totalValidos = totalValidos;
	}
		
}
