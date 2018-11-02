package pe.egcc.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.egcc.app.dto.ClienteDto;

@Service
public class EurekaService extends AbstractDaoSupport {

	public double getSaldoCuenta(String cuenta) {

		Double saldo = 0.0;
		try {
			String sql = "SELECT DEC_CUENSALDO FROM CUENTA WHERE CHR_CUENCODIGO = ?";
			Object[] param = { cuenta };
			saldo = jdbcTemplate.queryForObject(sql, param, Double.class);
		} catch (EmptyResultDataAccessException e) {
		}
		return saldo;

	}

	public ClienteDto getCliente(String idCliente) {
		ClienteDto rec = null;
		try {
			String sql = "SELECT " 
					+ "CHR_CLIECODIGO codigo, " 
					+ "VCH_CLIEPATERNO paterno,"
					+ "VCH_CLIEMATERNO materno, " 
					+ "VCH_CLIENOMBRE nombre, " 
					+ "CHR_CLIEDNI dni, "
					+ "VCH_CLIECIUDAD ciudad, " 
					+ "VCH_CLIEDIRECCION direccion, " 
					+ "VCH_CLIETELEFONO telefono, "
					+ "VCH_CLIEEMAIL email " 
					+ "FROM CLIENTE " 
					+ "WHERE CHR_CLIECODIGO = ?";
			rec = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ClienteDto>(ClienteDto.class), idCliente);
		} catch (Exception e) {
			rec = null;
		}
		return rec;
	}

	public List<Map<String, Object>> getMovimientos(String cuenta) {
		List<Map<String, Object>> lista;
		String sql = "select SUCUCODIGO, SUCUNOMBRE, CLIECODIGO, "
				+ "CLIEPATERNO, CLIEMATERNO, CLIENOMBRE, CUENCODIGO, "
				+ "CUENSALDO, CUENESTADO, MOVINUMERO, MOVIFECHA, "
				+ "MOVIIMPORTE, CUENREFERENCIA, TIPOCODIGO, TIPONOMBRE, " + "TIPOACCION, MONECODIGO, MONENOMBRE "
				+ "frOM V_MOVIMIENTO WHERE CUENCODIGO = ? ";
		lista = jdbcTemplate.queryForList(sql, cuenta);
		return lista;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void procDeposito(String cuenta, double importe, String codEmp) {
		// Variables
		String sql;
		Map<String, Object> map;
		double saldo;
		int cont;
		// Paso 1: Leer datos de la cuenta
		sql = "select dec_cuensaldo, int_cuencontmov  " 
				+ "from cuenta where chr_cuencodigo = ? " 
				+ "for update ";
		map = jdbcTemplate.queryForMap(sql, cuenta);
		saldo = Double.parseDouble(map.get("dec_cuensaldo").toString());
		cont = Integer.parseInt(map.get("int_cuencontmov").toString());
		// Paso 2: Actualizar cuenta
		saldo += importe;
		cont++;
		sql = "update cuenta set dec_cuensaldo = ?, " + "int_cuencontmov = ? " + "where chr_cuencodigo = ? ";
		jdbcTemplate.update(sql, saldo, cont, cuenta);
		// Paso 3: Registrar movimiento
		sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
				+ "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) " + "values(?,?,SYSDATE,?,'003',?)";
		jdbcTemplate.update(sql, cuenta, cont, codEmp, importe);
	}
	
	
	public void procRetiro(String cuenta, double importe,  String codEmp, String clave) {
		jdbcTemplate.update("call usp_egcc_retiro (?, ?, ?, ?)", 
				cuenta, importe, codEmp, clave);
	}


}
