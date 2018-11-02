package pe.sde.cinet.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.sde.cinet.model.entity.Establecimiento;
import pe.sde.cinet.model.entity.PrintConfiguration;

public interface IPrintConfigurationDao extends CrudRepository<PrintConfiguration, Long> {

	@Query("SELECT p FROM PrintConfiguration p WHERE p.establecimiento =?1")
	PrintConfiguration findByIdEstablecimiento(Establecimiento establecimiento);

}
