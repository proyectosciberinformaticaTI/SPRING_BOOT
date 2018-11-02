package pe.sde.cinet.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.sde.cinet.model.entity.ReporteBean;
import pe.sde.cinet.model.entity.ReporteParcialBean;

@Repository
public class TicketDaoImpl implements ITicketNativeDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<ReporteBean> obtenerReporte()  throws Exception {
		List<?> lisObject = null;
		List<ReporteBean> lisReporte = new ArrayList<>();
		String consulta = null;
		consulta = "SELECT year(T.fecha_registro) as ano  , month(T.fecha_registro) as mes, day(T.fecha_registro) as dia, count(t.codigo_barra) as ticket  ,sum(A.precio) as ingreso " +
				"FROM ticket T " +
				"inner join db_cinet.tarifa A " +
				" on t.tarifa_id= A.id " +
				" WHERE t.estado != '4' AND t.estado != '-1' " +
				" group by year(T.fecha_registro),month(T.fecha_registro), day(T.fecha_registro);" ;
		Query query = em.createNativeQuery(consulta);
		lisObject = query.getResultList();
		for (Object obj : lisObject) {
			Object[] object = (Object[]) obj;
			ReporteBean reporte = new ReporteBean();
			reporte.setAno(object[0].toString());
			reporte.setMes(object[1].toString());
			reporte.setDia(object[2].toString());
			reporte.setTotalTikets(Integer.parseInt(object[3].toString()));
			reporte.setTotalIngresos(Double.parseDouble(object[4].toString()));
			lisReporte.add(reporte);			
		}
		System.out.println(lisReporte.size());
		return lisReporte;
	}

	@Override
	@Transactional(readOnly=true)
	public String countTicketforDay() throws Exception {
		String consulta = null;
		Object obj = null;
		String cont = "1";
		try {
			consulta =" SELECT count(T.codigo_barra) AS contador " +
					" FROM ticket T " +
					" WHERE  DATE_FORMAT(T.fecha_registro, '%Y-%m-%d') = DATE_FORMAT( now(), '%Y-%m-%d') " +
					" AND T.estado <> '4' AND T.estado <> '-1' "; //Estado 4 Cancelado.
			Query query = em.createNativeQuery(consulta);
			obj = query.getSingleResult();
			if(obj!=null){
				cont = (Integer.parseInt(obj.toString())+1)+"";
			}
		} catch (Exception e) {
			throw new Exception("Error al contar tickets, " + e.getMessage());
		}
		
		return cont;
	}

	@Override
	@Transactional(readOnly=true)
	public String [] getTotalTicketVU(Date date) throws Exception {
		String [] totales = {"0","0"};
		List<?> lisObject = null;
		String consulta = null;
		consulta = " SELECT count(T.codigo_barra) AS vendidos " +
				" FROM ticket T " +
				" WHERE  DATE_FORMAT(T.fecha_registro, '%Y-%m-%d') = DATE_FORMAT( now() , '%Y-%m-%d') " +
				" AND T.estado <> '4' AND T.estado <> '-1' " +
				" UNION " +
				" SELECT count(T.codigo_barra) AS usados " +
				" FROM ticket T " +
				" WHERE  DATE_FORMAT(T.fecha_registro, '%Y-%m-%d') = DATE_FORMAT( now() , '%Y-%m-%d') " +
				" AND T.estado = '2' ";
		Query query = em.createNativeQuery(consulta);
		lisObject = query.getResultList();
		int i=0;
		for (Object obj : lisObject) {
			totales[i]= obj.toString();
			i++;
		}
		return totales;
	}

	@Override
	public ReporteParcialBean getReporteParcial(String userName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTicketTemp() throws Exception {
		String consulta = null;
		int result = 999;
		try {
			consulta ="EliminaTemporalesPasados";
			StoredProcedureQuery   query = em.createNamedStoredProcedureQuery(consulta);
			query.execute();
		} catch (Exception e) {
			throw new Exception("Error al eliminar temporales, " + result + ", " + e.getMessage());
		}
	}

	@Override
	public String countTicketTemp() throws Exception {
		String consulta = null;
		Object obj = null;
		String cont = "1";
		try {
			consulta =" SELECT count(T.codigo_barra) AS contador " +
					" FROM ticket T " +
					" WHERE  T.estado = '-1'; ";
			Query query = em.createNativeQuery(consulta);
			obj = query.getSingleResult();
			if(obj!=null){
				cont = obj.toString();
			}
		} catch (Exception e) {
			throw new Exception("Error al contar tickets, " + e.getMessage());
		}
		
		return cont;
	}
}
