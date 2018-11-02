package pe.sde.cinet.model.service;

import java.util.List;

import javax.validation.Valid;

import pe.sde.cinet.model.entity.PrintConfiguration;
import pe.sde.cinet.model.entity.Role;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Usuario;

public interface IMantenimientoService {

	public List<Usuario> findAllUser();

	public List<Role> findAllRole();

	public void saveUsuario(Usuario usuario, String userNameReg);

	public Usuario findUserById(Long id);
	
	public void saveRole(String idUsuario, String rol);

	public void deleteRoleById(Long idRole);

	public PrintConfiguration getPrintConfigByUser(String usuario);

	public void savePrintConfiguration(PrintConfiguration printConfig);

	public List<Tarifa> findAllTarifas();

	public void activarTarifa(Long idTarifa);

	public void saveTarifa(@Valid Tarifa tarifa);

	public void saveTarifa(@Valid Tarifa tarifa, String usuario);

	public void disabledUser(Long idUsuario);

	public Usuario findUserByUserName(String username);

}
