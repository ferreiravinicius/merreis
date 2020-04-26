package br.com.brigade.merreis.common.helpers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateHelperTest {
	
	@Test
	public void it_should_convert_string_to_local_date_using_brazillian_pattern() {
		final String textDate = "20/09/1994";
		LocalDate dateConverted = DateHelper.toLocalDate(textDate);
		assertEquals(LocalDate.of(1994, 9, 20), dateConverted);
	}
	
	@Test
	public void it_should_return_null_when_given_empty_or_invalid_string() {
		final String textEmpty = "";
		final String  textInvalid = "25/111/29";
		
		LocalDate emptyConverted = DateHelper.toLocalDate(textEmpty);
		assertNull(emptyConverted);
		
		LocalDate invalidConverted = DateHelper.toLocalDate(textInvalid);
		assertNull(invalidConverted);
	}
	
	@Test
	public void it_should_return_util_date_when_given_valid_string_to_convert_at_to_date() {
		String text = "20/09/1994";
		Date date = DateHelper.toDate(text);
		assertNotNull(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		assertEquals(calendar.get(Calendar.YEAR), 1994);
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 20);
		assertEquals(calendar.get(Calendar.MONTH), Calendar.SEPTEMBER);
	}
	
	@Test
	public void it_should_return_null_when_given_invalid_string_to_convert_at_to_date() {
		String textInvalid = "202/09/1994";
		Date date = DateHelper.toDate(textInvalid);
		assertNull(date);
	}	
	
	@Test
	public void it_should_return_valid_string_pattern_when_given_date_util() {
		String text = "20/09/1994";
		Date dateUtil = DateHelper.toDate(text);
		String outputDateText = DateHelper.toString(dateUtil);
		assertEquals(outputDateText, text);
	}
	
	@Test
	public void it_should_convert_util_date_to_local_date() {
		Date dateUtil = DateHelper.toDate("20/01/2020");
		LocalDate dateLocal = DateHelper.toLocalDate(dateUtil);
		
		assertNotNull(dateLocal);
		assertEquals(dateLocal.getYear(), 2020);
		assertEquals(dateLocal.getDayOfMonth(), 20);
		assertEquals(dateLocal.getMonth(), Month.JANUARY);
	}
	
	@Test
	public void it_should_return_null_when_converting_null_to_local_date() {
		String emptyText = null;
		Date emptyDate = null;
		LocalDate fromString = DateHelper.toLocalDate(emptyText);
		LocalDate fromDate = DateHelper.toLocalDate(emptyDate);
		assertNull(fromString);
		assertNull(fromDate);
	}
	
	@Test
	public void it_should_covert_local_date_to_string_pattern() {
		String expectedText = "01/01/2020";
		LocalDate date = DateHelper.toLocalDate(expectedText);
		String dateText = DateHelper.toString(date);
		assertNotNull(dateText);
		assertEquals(expectedText, dateText);
		
	}
}
