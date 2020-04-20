package br.com.brigade.merreis.common.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

	final static public String SIMPLE_BRAZIL_DATE = "dd/MM/yyyy";
	
	public static LocalDate toLocalDate(String dateText) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_BRAZIL_DATE);
			return LocalDate.parse(dateText, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

}
