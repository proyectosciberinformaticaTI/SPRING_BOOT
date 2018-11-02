package pe.sde.cinet.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.sde.cinet.model.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	public Usuario findByUsername(String username);

	@Query("select u from Usuario u Join u.roles r Where r.authority=?1") 
	public List<Usuario> findAllUserWithRoleByRole(String roleCajero);
	
	@Query("SELECT u FROM Usuario u Where u.enabled=true")
	public List<Usuario> findAllEnabled();

}
