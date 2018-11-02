package pe.sde.cinet.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import pe.sde.cinet.model.util.FechaUtil;

@Entity
@Table(name = "establecimiento")
public class Establecimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//LOCAL
	@NotNull
	private String nombre;
	private String direccionLocal;
	private String distrito;
	private String telefono;
	
	//EMPRESA
	private String rasonSocial;
	private String direccionEmpresa;
	private String ruc;
	
	
	private String logo;
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccionLocal() {
		return direccionLocal;
	}


	public void setDireccionLocal(String direccionLocal) {
		this.direccionLocal = direccionLocal;
	}


	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}


	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}


	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getRasonSocial() {
		return rasonSocial;
	}


	public void setRasonSocial(String rasonSocial) {
		this.rasonSocial = rasonSocial;
	}


	public String getDistrito() {
		return distrito;
	}


	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}


	@Override
	public String toString() {
		return nombre;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	
}
