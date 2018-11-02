package com.proyecto.general.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.general.bean.moderadorBean;
import com.proyecto.general.dao.espec.ModeradorDaoEspec;



@Repository
public class moderadorDaoimpl extends AbstractDao implements ModeradorDaoEspec{

	@Override
	public moderadorBean traerPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<moderadorBean> traerPorNombre(String dni) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void insertar(moderadorBean bean) {
		// TODO Auto-generated method stub
	
		
		
	    String sql = "select max(IDTB_MODERADOR) as IDTB_MODERADOR "
		        + "from TB_MODERADOR ";

		    Map<String, Object> map;
		    map = jdbcTemplate.queryForMap(sql);
		    int cont = Integer.parseInt(map.get("IDTB_MODERADOR").toString());
		
		    cont++;

		    Object[] args = {cont};

		    sql = "insert into TB_MODERADOR(IDTB_MODERADOR,USUARIO,CLAVE,NOMBRE_APELLIDO,ENTIDAD,TELEFONO,CORREO,DNI) values(?,?,?,?,?,?,?,?)";
		    args = new Object[]{cont, bean.getUSUARIO(),bean.getCLAVE(), bean.getNOMBRE_APELLIDO(), bean.getENTIDAD(),bean.getTELEFONO(), bean.getCORREO(), bean.getDNI()};
		    jdbcTemplate.update(sql, args);
		   




	}

	@Override
	public void actualizar(moderadorBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(String codigo) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public moderadorBean login(String usuario, String clave) {
		// TODO Auto-generated method stub
	moderadorBean bean=null;
		try {
		      String sql = "select "
		          + "IDTB_MODERADOR IDTB_MODERADOR, "
		          + "USUARIO USUARIO, "
		          + "CLAVE CLAVE "
		          + "from TB_MODERADOR "
		          + "where USUARIO = ? "
		          + "and CLAVE = ?";
		      Object[] args = {usuario, clave};
		      bean = jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper(moderadorBean.class));
		    } catch (EmptyResultDataAccessException e) {
		      bean = null;
		    }
		    return bean;

	}

	
	
	
}
