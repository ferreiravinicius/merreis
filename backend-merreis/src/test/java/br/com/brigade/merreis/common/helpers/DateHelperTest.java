package br.com.brigade.merreis.common.helpers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateHelperTest {
	
	@Test
	public void shouldConvertStringLocalDateUsingBrazillianCommonPattern() {
		final String textDate = "20/09/1994";
		LocalDate dateConverted = DateHelper.toLocalDate(textDate);
		assertEquals(LocalDate.of(1994, 9, 20), dateConverted);
	}
	
	@Test
	public void shouldReturnNullWhenGivenEmptyOrInvalidString() {
		final String textEmpty = "";
		final String  textInvalid = "25/111/29";
		
		LocalDate emptyConverted = DateHelper.toLocalDate(textEmpty);
		assertNull(emptyConverted);
		
		LocalDate invalidConverted = DateHelper.toLocalDate(textInvalid);
		assertNull(invalidConverted);
	}
}
