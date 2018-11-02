package pe.sde.cinet.model.dao;

import org.springframework.data.repository.CrudRepository;

import pe.sde.cinet.model.entity.Establecimiento;

public interface IEstablecimientoDao extends CrudRepository<Establecimiento, Long>{
	
	//@Query("select e from Estableciemiento e where responable =?1")
	//public Establecimiento findByUsuario(Usuario usuario);
}
