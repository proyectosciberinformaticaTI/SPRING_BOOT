package com.proyecto.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.proyecto.bean.agraviadosBean;
import com.proyecto.dao.espec.AgraviadosDaoEspec;

@Repository
public class agraviadoDaoimpl extends AbstractDao implements AgraviadosDaoEspec{

	@Override
	public agraviadosBean login(String usuario, String clave) {
		agraviadosBean cliente = null;
		try{
			List<Map<String,Object>> lista = null;
			Object[] parametros = new Object[]{usuario,clave};
			String sql = "select * from TB_AGRAVIADOS where USUARIO = ciber123 and CONTRASEÑA = ciber123";
			lista = jdbcTemplate.queryForList(sql,parametros);
			
			if(lista.size() != 0){
				for (Map<String,Object> item : lista) {
					cliente = new agraviadosBean();
					cliente.setNOMBRE_APELLIDO((String)item.get("NOMBRE_APELLIDO"));
					cliente.setDNI((Integer)item.get("DNI"));
					cliente.setTELEFONO((Integer)item.get("TELEFONO"));
					cliente.setCELULAR((Integer)item.get("CELULAR"));
					cliente.setCONTRASEÑA((String)item.get("CONTRASEÑA"));
					cliente.setUSUARIO((String)item.get("USUARIO"));
					cliente.setDIRECCION((String)item.get("DIRECCION"));
				    cliente.setCORREO((String)item.get("CORREO"));
				
				}
			}
		}catch(Exception e){
			System.out.println("Error de autenticacion");
		}
		return cliente;
	}
	
	
	  public static void main(String[] args) {
		     agraviadoDaoimpl dao = new agraviadoDaoimpl();
	  dao.login("ciber123","ciber123");
			  
		    }
	
	

	
	
	
	
	
	
	
}
