package pe.sde.cinet.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pe.sde.cinet.model.util.FechaUtil;

@Entity
@Table(name = "tarifa")
public class Tarifa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotNull
	private String categoria;
	
	@NotNull	
	private BigDecimal precio;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="establecimiento_id")
	private Establecimiento local;
	
	private boolean estado;
	
	
	private String usuReg;
	private String fecReg;
	private String usuMod;
	private String fecMod;
	
	
	

	@PrePersist
	public void prePersist() {
		if(fecReg!=null){
			fecReg = FechaUtil.dateTSToString(new Date());
		}else{
			fecMod = FechaUtil.dateTSToString(new Date());
		}
	}
	
	
	public String getUsuReg() {
		return usuReg;
	}
	public void setUsuReg(String usuReg) {
		this.usuReg = usuReg;
	}
	public String getFecReg() {
		return fecReg;
	}
	public void setFecReg(String fecReg) {
		this.fecReg = fecReg;
	}
	public String getUsuMod() {
		return usuMod;
	}
	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}
	public String getFecMod() {
		return fecMod;
	}
	public void setFecMod(String fecMod) {
		this.fecMod = fecMod;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	public Establecimiento getLocal() {
		return local;
	}


	public void setLocal(Establecimiento local) {
		this.local = local;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public String getEstadoActivo(){
		return estado?"Activo":"Inactivo";
	}

}
