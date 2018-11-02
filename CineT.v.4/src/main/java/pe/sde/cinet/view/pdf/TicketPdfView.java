package pe.sde.cinet.view.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pe.sde.cinet.model.entity.Ticket;
import pe.sde.cinet.model.util.FechaUtil;

@Component("/ticket/ver")
public class TicketPdfView extends AbstractPdfView {
	
	
	@Override
	protected Document newDocument() {
	  return new Document(new RectangleReadOnly(200,320), 1,1,1,1);
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		Ticket ticket = (Ticket) model.get("ticket");
						
		PdfPTable tablaEmpresa = new PdfPTable(1);
		tablaEmpresa.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		Font fontTE = new Font(Font.HELVETICA, 14, Font.BOLD);
		
		tablaEmpresa.addCell(new Phrase(ticket.getTarifa().getLocal().getNombre(),fontTE));
		
		fontTE = new Font(Font.HELVETICA, 8, Font.NORMAL);
				
		tablaEmpresa.addCell(new Phrase(ticket.getTarifa().getLocal().getRasonSocial(),fontTE));
		tablaEmpresa.addCell(new Phrase(ticket.getTarifa().getLocal().getDireccionEmpresa() + " RUC:" +ticket.getTarifa().getLocal().getRuc(),fontTE));		
		tablaEmpresa.addCell(new Phrase(ticket.getTarifa().getLocal().getDireccionLocal(),fontTE));
		tablaEmpresa.addCell(new Phrase(ticket.getTarifa().getLocal().getDistrito(),fontTE));
		
		
		PdfPTable tablafecha = new PdfPTable(1);
		tablafecha.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablafecha.addCell(new Phrase("Fecha: " +  FechaUtil.getDateWithPattern("dd/MM/yyyy", ticket.getFechaRegistro()),fontTE));
		tablafecha.addCell(new Phrase("Hora : " +  FechaUtil.getDateWithPattern("HH:mmy", ticket.getFechaRegistro()),fontTE));
		
		
		PdfPTable tablaDetalle = new PdfPTable(1);
		tablaDetalle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablaDetalle.addCell(new Phrase("Boleta de Ingreso: ",fontTE));
		
		BufferedImage bufferedImage = (BufferedImage) model.get("bufTicket");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", baos);
		Image iTextImage = Image.getInstance(baos.toByteArray());
		tablaDetalle.addCell(iTextImage);
		
		PdfPTable tablaDetalleTiket = new PdfPTable(3);
		tablaDetalleTiket.getDefaultCell().setBorder(Rectangle.BOTTOM);
		tablaDetalleTiket.addCell(new Phrase("Descripción",fontTE));
		tablaDetalleTiket.addCell(new Phrase("Cantidad",fontTE));
		tablaDetalleTiket.addCell(new Phrase("Importe",fontTE));
		
		tablaDetalleTiket.addCell(new Phrase("Boleto",fontTE));
		tablaDetalleTiket.addCell(new Phrase("1",fontTE));
		tablaDetalleTiket.addCell(new Phrase(ticket.getTarifa().getPrecio().toString(),fontTE));
		
		tablaDetalleTiket.addCell(new Phrase("Total",fontTE));
		tablaDetalleTiket.addCell(new Phrase("",fontTE));
		tablaDetalleTiket.addCell(new Phrase(ticket.getTarifa().getPrecio().toString(),fontTE));
		
		PdfPTable tablaDisclaimer = new PdfPTable(1);
		tablaDisclaimer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablaDisclaimer.addCell(new Phrase("Reclamar su comprobante de pago al salir del Establecimiento. Gracias por su preferencia.",fontTE));
		
		
		document.add(tablaEmpresa);
		document.add(tablafecha);
		document.add(tablaDetalle);
		document.add(tablaDetalleTiket);
		document.add(tablaDisclaimer);
		
		
	}
	

}
