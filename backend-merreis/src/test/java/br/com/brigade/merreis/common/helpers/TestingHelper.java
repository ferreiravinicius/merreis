package br.com.brigade.merreis.common.helpers;

import java.time.LocalDate;

import org.mockito.Mockito;

import br.com.brigade.merreis.api.models.ExpensePO;

public class TestingHelper {
	
	public static ExpensePO createExpenseWithValidFields() {
		return ExpensePO.builder()
				.description(Mockito.anyString())
				.date(LocalDate.now())
				.value(Mockito.any())
				.recurrency(Mockito.any())
				.build();
	}
}
