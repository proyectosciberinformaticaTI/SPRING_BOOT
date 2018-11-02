package pe.sde.cinet.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.sde.cinet.model.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long> {

	@Query("select t from Ticket t where t.codigoBarra=?1")
	public Ticket findBycodigoBarra(String codBarra);

	//corregir funcion
//	@Query("select t from Ticket t where t.usuReg=?1 and t.fechaRegistro  ")
//	public List<Ticket> findToDayForUser(Long usuario, String today);

	@Query("select t from Ticket t where t.usuReg=?1 and t.fechaRegistro = CURRENT_DATE ORDER BY t.id DESC ")
	public List<Ticket> findAllToDayByUser(Long id);

	@Query("select t from Ticket t where t.fechaRegistro = CURRENT_DATE ORDER BY t.id DESC ")
	public List<Ticket> findAllToDay();

	@Query("select t from Ticket t where t.fechaRegistro = CURRENT_DATE ORDER BY t.id DESC ")
	public String countTicketTemp();

}
