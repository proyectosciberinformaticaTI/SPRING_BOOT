package pe.egcc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDaoSupport {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
}
