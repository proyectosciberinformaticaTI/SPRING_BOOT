package com.bolsadeideas.springboot.app.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tb_usuario")
public class usuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long idusuario;


@Column(name="nombre")
private String nombre;

@Column(name="apellido")
private String apellido;

public Long getIdusuario() {
	return idusuario;
}

public void setIdusuario(Long idusuario) {
	this.idusuario = idusuario;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}







	
	
}
