package pe.sde.cinet.model.dao;

import java.util.Date;
import java.util.List;

import pe.sde.cinet.model.entity.ReporteBean;
import pe.sde.cinet.model.entity.ReporteParcialBean;

public interface ITicketNativeDao {
	
	public List<ReporteBean> obtenerReporte()  throws Exception;
	
	public String countTicketforDay() throws Exception;

	public String[] getTotalTicketVU(Date date) throws Exception;

	public ReporteParcialBean getReporteParcial(String userName) throws Exception;

	public void deleteTicketTemp()throws Exception;

	public String countTicketTemp()throws Exception;
	
}
