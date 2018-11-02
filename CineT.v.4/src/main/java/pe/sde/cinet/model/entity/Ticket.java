package pe.sde.cinet.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import pe.sde.cinet.model.util.ConstantesUtil;
import pe.sde.cinet.model.util.FechaUtil;

@Entity
@Table(name = "ticket")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "EliminaTemporalesPasados",
                                procedureName = "EliminaTemporalesPasados"
                               )
})
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigoBarra;

	@NotNull
	private String estado;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_registro")
	private Date fechaRegistro;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm")
	@Column(name = "hora_registro")
	private Date horaRegistro;

	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
		horaRegistro = new Date();
		fechaCaducidad = new Date();		
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	@Column(name = "fecha_uso")
	private Date fechaUso;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	@Column(name = "fecha_caducidad")
	private Date fechaCaducidad;

	@NotNull
	private Long usuReg;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tarifa_id")
	private Tarifa tarifa;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaUso() {
		return fechaUso;
	}

	public void setFechaUso(Date fechaUso) {
		this.fechaUso = fechaUso;
	}

	public Long getUsuReg() {
		return usuReg;
	}

	public void setUsuReg(Long usuReg) {
		this.usuReg = usuReg;
	}

	@Override
	public String toString() {
		return codigoBarra;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public String getDia(){
		return FechaUtil.getDateWithPattern("dd/MM/yyyy",this.getFechaRegistro());
	}
	public String getHora(){
		return FechaUtil.getDateWithPattern("HH:mm",this.getHoraRegistro());
	}
	public String getImgCodBarra(){
		return codigoBarra + ".png";
	}
	
	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	
	
	public String getEstadoText(){
		Integer es = Integer.parseInt(estado);
		String estadoText = "";
		switch (es) {
		case 0:
			estadoText = "Creado";
			break;
		case 1:
			estadoText = "Impreso";
			break;
		case 2:
			estadoText = "Usado";
			break;
		case 3:
			estadoText = "Caducado";
			break;
		case 4:
			estadoText = "Cancelado";
			break;
		default:
			break;
		}
		return estadoText;
	}

	public boolean canCalcel(){
		if(estado.equals(ConstantesUtil.TICKET_ESTADO_CREADO.toString()) || estado.equals(ConstantesUtil.TICKET_ESTADO_IMPRESO.toString()))
			return true;
		return false;
	}
	
	
	
}
