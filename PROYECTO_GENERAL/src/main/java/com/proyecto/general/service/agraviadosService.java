package com.proyecto.general.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.proyecto.general.dao.espec.AgraviadosDaoEspec;
import com.proyecto.general.dao.impl.AbstractDao;
import com.proyecto.general.bean.agraviadosBean;

@Service
public class agraviadosService extends AbstractDao{

    

	  @Autowired
	  private AgraviadosDaoEspec crudUsuario;

    
    @SuppressWarnings("unchecked")
	public agraviadosBean login(String usuario, String clave) {
    	  if(usuario == null || usuario.isEmpty()){
    	      throw new RuntimeException("Faltan datos.");
    	    }
    	    if(clave == null || clave.isEmpty()){
    	      throw new RuntimeException("Faltan datos.");
    	    }

    	    agraviadosBean bean = crudUsuario.login(usuario, clave);
    	    if(bean == null){
    	      throw new RuntimeException("Datos incorrectos.");
    	    }
    	    return bean;

	  }
    
    
    public void insertar(agraviadosBean clienteBean){
        crudUsuario.insertar(clienteBean);
      }
    public List<agraviadosBean> traerPorNombre(String dni){
    	List<agraviadosBean> lista;
		String sql = "select ID_CODIGO ID_CODIGO, NOMBRE_APELLIDO NOMBRE_APELLIDO, DNI DNI, "
				+ "TELEFONO TELEFONO, CELULAR CELULAR, CORREO CORREO, USUARIO USUARIO, "
				+ "DIRECCION DIRECCION, CLAVE CLAVE frOM TB_AGRAVIADOS WHERE DNI = ? ";
		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<agraviadosBean>(agraviadosBean.class),dni);
		return lista;
    }


}
