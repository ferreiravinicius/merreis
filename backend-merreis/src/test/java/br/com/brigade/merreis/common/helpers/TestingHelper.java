package br.com.brigade.merreis.common.helpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;

public class TestingHelper {
	
	public final static String VALID_DATE_STR = "20/09/2020";
	public final static String VALID_VALUE_STR = "50.00";
	public final static String VALID_DESCRIPTION_STR = "This is valid description";
	public final static RecurrencyEnum VALID_RECURRENCY_ENM = RecurrencyEnum.MONTHLY;
	
	final static Faker faker = new Faker();
	
	public static ExpenseRequestDTO createExpenseRequestValidated() {
		return ExpenseRequestDTO.builder()
				.date(VALID_DATE_STR)
				.value(VALID_VALUE_STR)
				.recurrency(VALID_RECURRENCY_ENM)
				.description(VALID_DESCRIPTION_STR)
				.build();
	}
	
	public static ExpensePO createExpenseValidated() {
		return ExpensePO.builder()
				.date(LocalDate.now())
				.value(new BigDecimal("50.00"))
				.recurrency(VALID_RECURRENCY_ENM)
				.description(VALID_DESCRIPTION_STR)
				.build();
	}
	
	public static ExpenseRequestDTO randomizeExpenseRequest() {
		String dateText = DateHelper.toString(faker.date().past(30, TimeUnit.DAYS));
		return ExpenseRequestDTO.builder()
				.value(faker.numerify("##.##"))
				.id(faker.number().randomNumber())
				.description(faker.book().title())
				.recurrency(RecurrencyEnum.MONTHLY)
				.date(dateText)
				.build();
	}
}
