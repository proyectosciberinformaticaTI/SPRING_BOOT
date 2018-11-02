package com.proyecto.general.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.proyecto.general.bean.agraviadosBean;
import com.proyecto.general.bean.moderadorBean;
import com.proyecto.general.dao.espec.ModeradorDaoEspec;
import com.proyecto.general.dao.impl.AbstractDao;

@Service
public class moderadorService extends AbstractDao{
    
	
	@Autowired
	private ModeradorDaoEspec crudModerador;
	
	 
    @SuppressWarnings("unchecked")
	public moderadorBean login(String usuario, String clave) {
    	  if(usuario == null || usuario.isEmpty()){
    	      throw new RuntimeException("Faltan datos.");
    	    }
    	    if(clave == null || clave.isEmpty()){
    	      throw new RuntimeException("Faltan datos.");
    	    }

    	    moderadorBean bean = crudModerador.login(usuario, clave);
    	    if(bean == null){
    	      throw new RuntimeException("Datos incorrectos.");
    	    }
    	    return bean;

	  }
    public List<moderadorBean>traerPorNombre(String dni){
    	
     	List<moderadorBean> lista;
    		String sql = "select IDTB_MODERADOR IDTB_MODERADOR,NOMBRE_APELLIDO NOMBRE_APELLIDO, USUARIO USUARIO, DNI DNI,CLAVE CLAVE, TELEFONO TELEFONO, CORREO CORREO, ENTIDAD ENTIDAD FROM TB_MODERADOR WHERE DNI = ? ";
    		lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<moderadorBean>(moderadorBean.class),dni);
    		return lista;
    }
    
    public void insertar(moderadorBean bean){
    	crudModerador.insertar(bean);
    }
	
}
