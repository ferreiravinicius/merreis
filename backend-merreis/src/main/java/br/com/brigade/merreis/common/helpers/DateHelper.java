package br.com.brigade.merreis.common.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.time.DateUtils;

public class DateHelper {

	final static public String SIMPLE_DATE_PATTERN = "dd/MM/yyyy";
	
	public static LocalDate toLocalDate(String dateText) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_DATE_PATTERN);
			return LocalDate.parse(dateText, formatter);
		} catch (Exception ignored) {
			return null;
		}
	}
	
	public static String toString(Date dateUtil) {
		SimpleDateFormat formatter = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		return formatter.format(dateUtil);
	}

	public static Date toDate(String dateText) {
		try {
			return DateUtils.parseDateStrictly(dateText, SIMPLE_DATE_PATTERN);
		} catch (ParseException ignored) {
			return null;
		}
	}

	public static LocalDate toLocalDate(Date dateUtil) {
		if (Objects.nonNull(dateUtil)) {
			return dateUtil.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		}
		return null;
	}

	public static String toString(LocalDate date) {
		if (Objects.nonNull(date)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_DATE_PATTERN);
			return date.format(formatter);
		}
		return null;
	}
	
}
