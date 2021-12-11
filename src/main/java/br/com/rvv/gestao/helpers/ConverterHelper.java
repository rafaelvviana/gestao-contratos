package br.com.rvv.gestao.helpers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public final class ConverterHelper {

	public static BigDecimal bigDecimal(String pValorBigDecimal) {		
		if ((pValorBigDecimal == "") || (pValorBigDecimal == "0.0") || pValorBigDecimal == null) {
			pValorBigDecimal = "0";
		}
		return new BigDecimal(pValorBigDecimal);
	}
	
	public static long valorLong(String pValor) {
		if (pValor == "") {
			pValor = "0";
		}
		float tempFloat = Float.parseFloat(pValor);
		int tempInt = (int) tempFloat;

		return tempInt;
	}
	
	public static LocalDate dataLocalDate(String pDataObtida) {
		try {
			if (pDataObtida == "") {
				return null;
			}
			DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
			Date date;
			date = (Date) formatter.parse(pDataObtida.toString());
			Instant instant = date.toInstant();			
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			LocalDate localDate = localDateTime.toLocalDate();
			return localDate;
		} catch (ParseException e) {
			return null;
		}
	}
}
