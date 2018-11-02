package com.bolsadeideas.springboot.app.service;

import java.util.List;

import com.bolsadeideas.springboot.app.entidad.usuarioBean;

public interface IUsuarioService {

	
	
	public List<usuarioBean> findAll();
	
	public void save(usuarioBean usuario);
	
	public usuarioBean findOne(Long id);

	public void delete(Long id);
	
	
	
	
}
