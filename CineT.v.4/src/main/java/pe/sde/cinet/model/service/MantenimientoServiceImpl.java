package pe.sde.cinet.model.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.sde.cinet.model.dao.IPrintConfigurationDao;
import pe.sde.cinet.model.dao.IRoleDao;
import pe.sde.cinet.model.dao.ITarifaDao;
import pe.sde.cinet.model.dao.IUsuarioDao;
import pe.sde.cinet.model.entity.PrintConfiguration;
import pe.sde.cinet.model.entity.Role;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Usuario;

@Service
public class MantenimientoServiceImpl implements IMantenimientoService {

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IPrintConfigurationDao printDao;
	@Autowired
	private ITarifaDao tarifaDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<Usuario> findAllUser() {
//		return (List<Usuario>) usuarioDao.findAll();
		return (List<Usuario>) usuarioDao.findAllEnabled();
	}

	@Override
	public List<Role> findAllRole() {
		return (List<Role>) roleDao.findAll();
	}

	@Override
	public void saveUsuario(Usuario usuario, String userNameReg) {
		Usuario userReg = usuarioDao.findByUsername(userNameReg);		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEstablecimiento(userReg.getEstablecimiento());
		usuario.setEnabled(true);
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario findUserById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public void saveRole(String idUsuario, String rol) {
		Role authority = new Role();
		authority.setUserId(Long.parseLong(idUsuario));
		authority.setAuthority(rol);
		roleDao.save(authority);
		
	}

	@Override
	public void deleteRoleById(Long idRole) {
		roleDao.deleteById(idRole);
	}

	@Override
	public PrintConfiguration getPrintConfigByUser(String username) {
		Usuario usuario = usuarioDao.findByUsername(username);		
		return printDao.findByIdEstablecimiento(usuario.getEstablecimiento());
	}

	@Override
	public void savePrintConfiguration(PrintConfiguration printConfig) {
		printDao.save(printConfig);
	}

	@Override
	public List<Tarifa> findAllTarifas() {
		//return (List<Tarifa>) tarifaDao.findAll();
		return tarifaDao.findAllByIdDesc();
	}

	@Override
	public void activarTarifa(Long idTarifa) {
		//LITAR LOS DEMAS Y CAMBIAR ESTADO
		List<Tarifa> ltarifas =  (List<Tarifa>) tarifaDao.findAll();
		for (Tarifa tarifa2 : ltarifas) {
			tarifa2.setEstado(false);
		}
		tarifaDao.saveAll(ltarifas);
		Tarifa tarifa = tarifaDao.findById(idTarifa).orElse(null);
		tarifa.setEstado(true);
		tarifaDao.save(tarifa);
	}

	@Override
	public void saveTarifa(@Valid Tarifa tarifa) {		
		tarifaDao.save(tarifa);
	}

	@Override
	public void saveTarifa(@Valid Tarifa tarifa, String username) {
		List<Tarifa> ltarifas =  (List<Tarifa>) tarifaDao.findAll();
		for (Tarifa tarifa2 : ltarifas) {
			tarifa2.setEstado(false);
		}
		tarifaDao.saveAll(ltarifas);
		
		Usuario usuario = usuarioDao.findByUsername(username);
		tarifa.setLocal(usuario.getEstablecimiento());
		tarifa.setEstado(true);
		tarifaDao.save(tarifa);		
	}

	@Override
	public void disabledUser(Long idUsuario) {
		Usuario usuario = usuarioDao.findById(idUsuario).orElse(null);
		usuario.setEnabled(false);
		usuarioDao.save(usuario);		
	}

	@Override
	public Usuario findUserByUserName(String username) {		
		return usuarioDao.findByUsername(username);
	}

}
