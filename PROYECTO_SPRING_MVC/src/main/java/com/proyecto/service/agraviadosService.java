package com.proyecto.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bean.agraviadosBean;
import com.proyecto.dao.espec.AgraviadosDaoEspec;
import com.proyecto.dao.impl.AbstractDao;

@Service
public class agraviadosService extends AbstractDao {
	
    @Autowired
	private AgraviadosDaoEspec agraviadosDAO;
	
	
	
    public agraviadosBean login(String usuario,String clave){
    	agraviadosBean cliente = null;
		try{
			List<Map<String,Object>> lista = null;
			Object[] parametros = new Object[]{usuario,clave};
			String sql = "select * from TB_AGRAVIADOS where USUARIO = ? and CONTRASEÑA = ?";
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
	
	
	
	
	
}
