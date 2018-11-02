package pe.sde.cinet.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "print_configuration")
public class PrintConfiguration implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String pathExePrint;
	@NotEmpty
	private String pathPrintBarcode;
	@NotEmpty
	private String namePrint;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Establecimiento establecimiento;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPathExePrint() {
		return pathExePrint;
	}
	public void setPathExePrint(String pathExePrint) {
		this.pathExePrint = pathExePrint;
	}
	public String getPathPrintBarcode() {
		return pathPrintBarcode;
	}
	public void setPathPrintBarcode(String pathPrintBarcode) {
		this.pathPrintBarcode = pathPrintBarcode;
	}
	public String getNamePrint() {
		return namePrint;
	}
	public void setNamePrint(String namePrint) {
		this.namePrint = namePrint;
	}
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	
	
	
}
