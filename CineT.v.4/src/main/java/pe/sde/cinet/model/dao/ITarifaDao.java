package pe.sde.cinet.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.sde.cinet.model.entity.Tarifa;

public interface ITarifaDao extends CrudRepository<Tarifa, Long> {

	@Query("SELECT t FROM Tarifa t WHERE t.estado=1")
	Tarifa findByEstadoActivo();

	@Query(value="SELECT * FROM tarifa ORDER BY id DESC LIMIT 2", nativeQuery = true)
	List<Tarifa> findAllByIdDesc();

}
