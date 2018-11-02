package pe.sde.cinet.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sourceforge.jbarcodebean.BarcodeException;
import pe.sde.cinet.model.entity.ReporteBean;
import pe.sde.cinet.model.entity.ReporteParcialBean;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Ticket;
import pe.sde.cinet.model.entity.Usuario;

public interface ITicketService {
	public List<Ticket> findAll();
	public void save(Ticket tiket);
	
	public Map<String, Object> generaTicket(String usuario) throws IOException, BarcodeException;
	public Map<String, Object> findBycodigoBarra(String codBarra);
	public List<Ticket> findToDayForUser(String usuario);
	public void cancelaTicket(String codBarra);
	public List<ReporteBean> generarReporte() throws Exception;
	public String cuentaTickets() throws Exception;
	public String[] getTotalTicketVU() throws Exception;
	public List<Usuario> findAllUserByRole();
	public List<Ticket> findToDayAll();
	public List<Ticket> findToDayForIdUser(String idUsuario);
	public ReporteParcialBean generarReporteCorte(String usuario) throws Exception ;
	public Tarifa getTarifa();
		
}
