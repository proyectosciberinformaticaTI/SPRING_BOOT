package com.proyecto.general.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.general.bean.agraviadosBean;
import com.proyecto.general.dao.espec.AgraviadosDaoEspec;

@Repository
public class agraviadoDaoimpl extends AbstractDao implements AgraviadosDaoEspec{

	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public agraviadosBean login(String usuario, String clave) {
		   agraviadosBean bean = null;
		    try {
		      String sql = "select "
		          + "ID_CODIGO ID_CODIGO, "
		          + "USUARIO USUARIO, "
		          + "CLAVE CLAVE "
		          + "from TB_AGRAVIADOS "
		          + "where USUARIO = ? "
		          + "and CLAVE = ?";
		      Object[] args = {usuario, clave};
		      bean = jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper(agraviadosBean.class));
		    } catch (EmptyResultDataAccessException e) {
		      bean = null;
		    }
		    return bean;

	}
	
	



	@Override
	public agraviadosBean traerPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<agraviadosBean> traerPorNombre(String dni) {
		// TODO Auto-generated method stub
	return null;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void insertar(agraviadosBean bean) {
	
	
			 
			    String sql = "select max(ID_CODIGO) as ID_CODIGO "
			        + "from TB_AGRAVIADOS ";

			    Map<String, Object> map;
			    map = jdbcTemplate.queryForMap(sql);
			    int cont = Integer.parseInt(map.get("ID_CODIGO").toString());
			
			    cont++;

			    Object[] args = {cont};

			    sql = "insert into TB_AGRAVIADOS(ID_CODIGO,NOMBRE_APELLIDO,DNI,TELEFONO,CELULAR,CORREO,USUARIO,DIRECCION,CLAVE) values(?,?,?,?,?,?,?,?,?)";
			    args = new Object[]{cont, bean.getNOMBRE_APELLIDO(), bean.getDNI(),
				        bean.getTELEFONO(), bean.getCELULAR(), bean.getCORREO(),
				        bean.getUSUARIO(), bean.getDIRECCION(), bean.getCLAVE()};
			    jdbcTemplate.update(sql, args);
			   
	
	
	
	
	
	
	
	}


	@Override
	public void actualizar(agraviadosBean bean) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminar(String codigo) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	
	
	
	
	
}
