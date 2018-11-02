package pe.sde.cinet.model.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sourceforge.jbarcodebean.BarcodeException;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Ean13;
import pe.sde.cinet.model.dao.IPrintConfigurationDao;
import pe.sde.cinet.model.dao.ITarifaDao;
import pe.sde.cinet.model.dao.ITicketDao;
import pe.sde.cinet.model.dao.ITicketNativeDao;
import pe.sde.cinet.model.dao.IUsuarioDao;
import pe.sde.cinet.model.entity.PrintConfiguration;
import pe.sde.cinet.model.entity.ReporteBean;
import pe.sde.cinet.model.entity.ReporteParcialBean;
import pe.sde.cinet.model.entity.Tarifa;
import pe.sde.cinet.model.entity.Ticket;
import pe.sde.cinet.model.entity.Usuario;
import pe.sde.cinet.model.util.ConstantesUtil;
import pe.sde.cinet.model.util.FechaUtil;

@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketDao ticketDao;
	@Autowired
	private ITicketNativeDao ticketNativoDao;
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private ITarifaDao tarifaDao;
	@Autowired
	private  IPrintConfigurationDao printDao;

	
	private final static String UPLOAD_FOLDER = "uploads";
	
	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelaTicket(String codBarra) {
		Ticket ticket= ticketDao.findBycodigoBarra( codBarra);
		ticket.setEstado(ConstantesUtil.TICKET_ESTADO_CANCEADO.toString());
		ticketDao.save(ticket);
	}
	

	@Override
	@Transactional
	public void save(Ticket ticket) {
		ticketDao.save(ticket);
	}

	@Override
	public Map<String, Object> findBycodigoBarra(String codBarra) {
		Map<String, Object> respuesta = new HashMap<>();
		Ticket ticket= ticketDao.findBycodigoBarra( codBarra);
		
		if(ticket == null){
			respuesta.put("ticket", null);
			respuesta.put("ok", false);
			respuesta.put("detalle", "Codigo no Registrado en el sistema, consulte en Caja");
			return respuesta;
		}
		
		respuesta.put("ticket", ticket);
		
		switch (Integer.parseInt(ticket.getEstado())) {
		case 0://CREADO
				ticket.setEstado(ConstantesUtil.TICKET_ESTADO_USADO.toString());
				respuesta.put("ok", true);
				respuesta.put("detalle", "Ticket Registrado Correctamente!");
			break;
		case 1://imPRESO
			//si es del mismo Día.
			if(FechaUtil.getDateWithPattern("yyyMMdd", ticket.getFechaRegistro())
					.equals(FechaUtil.getDateWithPattern("yyyMMdd", ticket.getFechaRegistro()))){
				ticket.setEstado(ConstantesUtil.TICKET_ESTADO_USADO.toString());
				respuesta.put("ok", true);
				respuesta.put("detalle", "Ticket Registrado Correctamente!");
			}else{
				ticket.setEstado(ConstantesUtil.TICKET_ESTADO_CADUCADO.toString());
				respuesta.put("ok", false);
				respuesta.put("detalle", "Ticket Caducado!");
			}
			break;		
		case 2: //USADO
			respuesta.put("ok", false);
			respuesta.put("detalle", "Ticket ya fue Usado!");
			break;
		case 3:
			respuesta.put("ok", false);
			respuesta.put("detalle", "Ticket Caducado!");
			break;
		case 4:
			respuesta.put("ok", false);
			respuesta.put("detalle", "Ticket NO valido, está CANCELADO, consulte en Caja!");
			break;

		default:			
			break;
		}
		
		ticketDao.save(ticket);
		
		return respuesta;
	}
	
	@Override
	public Map<String, Object> generaTicket(String username) throws IOException, BarcodeException {
		Map<String, Object> resp = new HashMap<>();
		//Obtiene Usuario
		Usuario usuario = usuarioDao.findByUsername(username);
		
		//Obtiene tarifa //se obtiene la tarifa activa estado Activo;
		Tarifa tarifa = tarifaDao.findByEstadoActivo();
		
		//Pregunta si está libre impresion Ticket:
		
		while(crearTicketLibre()){
			//Esperando
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			}
		}
		
		
		Ticket ticket = new Ticket();
		ticket.setUsuReg(usuario.getId());
		ticket.setTarifa(tarifa);
		ticket.setEstado("-1");
	
		ticketDao.save(ticket);
		
		
		String numTicket = "0";
		try {
			numTicket = ticketNativoDao.countTicketforDay();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		///
		String codBarra = generaCodigoBarra(numTicket);
		ticket.setCodigoBarra(codBarra);
		ticket.setEstado("1");
		ticketDao.save(ticket);
		
		///
		BufferedImage bufTicket= creaCodigoBarras(codBarra);
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		try {
	        ImageIO.write(bufTicket, "png", bao);
		} catch (Exception e) {			
		}	
	    String b64 = DatatypeConverter.printBase64Binary(bao.toByteArray());   
		resp.put("bufTicket", bufTicket);
		resp.put("ticket", ticket);
		resp.put("b64", b64);
		
		//OBTIENE CONFIGURACION PRINT
		PrintConfiguration print = printDao.findByIdEstablecimiento(usuario.getEstablecimiento());
						
		@SuppressWarnings("unused")
		Process builder = new ProcessBuilder(
					print.getPathExePrint(),   //"A:\\SDE\\CineT\\Print\\ImpresionWEB.exe",
		    		codBarra.substring(0, 12),
					"S/"+tarifa.getPrecio().toString(),
					tarifa.getCategoria(),
					print.getNamePrint(),      //"\\\\192.168.1.131\\epson",
					print.getPathPrintBarcode()  //"C:\\print\\barr.png"
					,numTicket
					,codBarra
				).inheritIO().start();
		
		//Espera a que termine la impresión
		try {
			Thread.sleep(700);
		} catch (Exception e) {
		}
		return resp;
	}

	private boolean crearTicketLibre() {
		String numTicket = "0";
		try {
			//eliminar ticket de mas de 1 minuto en ocupado:
			ticketNativoDao.deleteTicketTemp();
			//preguntando por ticket ocupado:
			numTicket = ticketNativoDao.countTicketTemp();
			if(numTicket.equals("0"))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static BufferedImage creaCodigoBarras(String codigo) throws IOException, BarcodeException {
		JBarcodeBean barcode = new JBarcodeBean();

		// nuestro tipo de codigo de barra
		barcode.setCodeType(new Ean13());

		// barcode.setCodeType(new Code39());

		// nuestro valor a codificar y algunas configuraciones mas
		barcode.setCode(codigo);
		barcode.setCheckDigit(true);
//		System.out.println(barcode.getCode());
		
		BufferedImage bufferedImage = barcode.draw(new BufferedImage(110, 60, BufferedImage.TYPE_INT_ARGB));

		// guardar en disco como png
		///File file = new File("A:\\SDE\\CineT\\Ticket\\" + codigo + ".png");
		//ImageIO.write(bufferedImage, "png", file);
		
		BufferedImage codBar = resize(bufferedImage, 110, 200);

		
		
		return codBar;
	}
	
	public Path getPath(String fileName){
		return Paths.get(UPLOAD_FOLDER).resolve(fileName).toAbsolutePath();
	}

	public static String generaCodigoBarra(String correlativo) {

		String anomesdia = FechaUtil.getDateWithPattern("yyyyMMdd", new Date());		
		String cCorrelativo = completaCeros( correlativo, 4);
		Integer sum = 0;

		String codigo = anomesdia + cCorrelativo;

		// POR LA POSICION: NUMCONTROL = (10 - ((SUMA_IMPARES)*3 + SUMA_PARES))
		// % 10
		Integer sumP = 0;
		Integer sumI = 0;
		Integer res = 0;
		for (int i = 0; i < codigo.length(); i++) {
			switch (i % 2) {
			case 0:
				sumP += Integer.parseInt(codigo.substring(i, i + 1));
				break;
			case 1:
				sumI += Integer.parseInt(codigo.substring(i, i + 1));
				break;
			}
		}
		res = sumI*3 + sumP;
		sum = (10 - (res % 10)) % 10;
//		System.out.println("res:" + res + " dc " + sum);
		codigo = codigo + sum;
		return codigo;
	}

	private static String completaCeros(String valor, int tamano) {
		String tem = valor;
		for (int i = 0; i < (tamano - valor.length()); i++) {
			tem = "0" + tem;
		}
		// System.out.println("*\t" + tem);
		return tem;
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

	@Override
	public List<ReporteBean> generarReporte() throws Exception {
		return ticketNativoDao.obtenerReporte();
	}

	@Override
	public String cuentaTickets()  throws Exception{		
		return ticketNativoDao.countTicketforDay(); 
	}

	@Override
	public String [] getTotalTicketVU() throws Exception {
		return ticketNativoDao.getTotalTicketVU(new Date());  //Para el día en curso.
	}

	@Override
	public List<Usuario> findAllUserByRole() {
		return usuarioDao.findAllUserWithRoleByRole(ConstantesUtil.ROLE_CAJERO);
	}

	@Override
	public List<Ticket> findToDayAll() {		
		return ticketDao.findAllToDay();
	}

	@Override
	public List<Ticket> findToDayForUser(String username) {
		Usuario usuario = usuarioDao.findByUsername(username);
		List<Ticket> ltick = new ArrayList<>();
		int cont=0;
		for (Ticket tic : ticketDao.findAllToDayByUser(usuario.getId())) {
			ltick.add(tic);cont++;
			if(cont == 3)break;
		}
		return ltick;
	}

	@Override
	public List<Ticket> findToDayForIdUser(String idUsuario) {
		Usuario usuario = usuarioDao.findById(Long.parseLong(idUsuario)).orElse(null);
		return ticketDao.findAllToDayByUser(usuario.getId());
	}

	@Override
	public ReporteParcialBean generarReporteCorte(String username) throws Exception {
		Usuario usuario = usuarioDao.findByUsername(username);
		List<Ticket> lisTickets = ticketDao.findAllToDayByUser(usuario.getId());	
		int totalCancelado=0;
		int totalTickets=0;
		Double totalIngreso=0.0;
		totalTickets = lisTickets.size();
		ReporteParcialBean reporte = new ReporteParcialBean();
		reporte.setUsuario(usuario);
		reporte.setLocal(usuario.getEstablecimiento());
		reporte.setFechaHoraGeneracion(FechaUtil.getDateWithPattern("dd/MM/yyyy hh:mm", new Date()));
		reporte.setTotalTickets(totalTickets);		
		
		for (Ticket ticket : lisTickets) {		
			if(ticket.getEstado().equals(ConstantesUtil.TICKET_ESTADO_CANCEADO)){
				totalCancelado++;
			}else{
				totalIngreso += ticket.getTarifa().getPrecio().doubleValue();
			}
		}
		reporte.setTotalIngresos(totalIngreso);
		reporte.setTotalCancelados(totalCancelado);
		reporte.setTotalValidos(totalTickets-totalCancelado);
				
		return reporte;
	}

	@Override
	public Tarifa getTarifa() {
		return tarifaDao.findByEstadoActivo();
	}
	
}
