package com.bolsadeideas.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.dao.IUsuarioDAO;
import com.bolsadeideas.springboot.app.entidad.usuarioBean;

@Service
public class UsuarioServiceImpI implements IUsuarioService{


@Autowired	
private IUsuarioDAO usuarioDAO;

@Override
public List<usuarioBean> findAll() {
	// TODO Auto-generated method stub
	return (List<usuarioBean>) usuarioDAO.findAll();
}

@Override
public void save(usuarioBean usuario) {
	// TODO Auto-generated method stub
	
}

@Override
public usuarioBean findOne(Long id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void delete(Long id) {
	// TODO Auto-generated method stub
	
}	
	



	
	
	
}
