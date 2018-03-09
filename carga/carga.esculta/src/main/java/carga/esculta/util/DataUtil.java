package carga.esculta.util;

import java.util.Calendar;
import java.util.Locale;

public class DataUtil {

	public static String formatarDataCorrenteJuntas() {

		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
		
		String ano = String.valueOf(calendar.get(Calendar.YEAR));
		Integer mes = calendar.get(Calendar.MONTH) + 1;
		String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String minuto = String.valueOf(calendar.get(Calendar.MINUTE));
		String segundos = String.valueOf(calendar.get(Calendar.SECOND));
		String milisegundos = String.valueOf(calendar.get(Calendar.MILLISECOND)); 

		return ano + String.format("%02d", mes)  + dia + minuto + segundos + milisegundos;

	}

}
