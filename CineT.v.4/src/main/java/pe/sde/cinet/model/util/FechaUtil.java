package pe.sde.cinet.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class FechaUtil {
	
	/**
	 * Retorna String de fecha en formato: "yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String dateTSToString(Date date){
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * Retorna String de fecha en formato: "yyyyMM"
	 * @param date
	 * @return
	 */
	public static String getYerarMonth(){		
		String pattern = "yyyyMM";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	}
	
	public static String getDateWithPattern(String pattern, Date date){		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	public static Date generaCaducidad() throws ParseException{
		String dia = "";		
		String pattern = "YYY-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		dia = simpleDateFormat.format(new Date());
		pattern = "YYY-MM-dd HH:mm";
		simpleDateFormat = new SimpleDateFormat(pattern);
		Date fecha = simpleDateFormat.parse(dia + "23:59");
		return fecha;
	}

}