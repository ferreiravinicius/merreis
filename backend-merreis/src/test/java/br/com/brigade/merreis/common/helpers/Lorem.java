package br.com.brigade.merreis.common.helpers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.transfers.ExpenseOutputDTO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;

public class Lorem {
	
	public static Faker faker = Faker.instance();
	
	public static class ExpenseOutput {
		
		public static ExpenseOutputDTO random() {
			
			String fakeDateText = DateHelper.toString(faker.date().past(30, TimeUnit.DAYS));
			BigDecimal fakeNumber = new BigDecimal(faker.numerify("##.##"));
			
			return ExpenseOutputDTO.builder()
					.value(fakeNumber)
					.date(fakeDateText)
					.description(faker.yoda().quote())
					.id(faker.number().randomNumber())
					.recurrency(RecurrencyEnum.MONTHLY)
					.build();
		}
		
	}
	
	public static class ExpenseRequest {
		
		public static ExpenseRequestDTO random() {
			
			String fakeDateText = DateHelper.toString(faker.date().past(30, TimeUnit.DAYS));
			
			return ExpenseRequestDTO.builder()
					.date(fakeDateText)
					.value(faker.numerify("##.##"))
					.id(faker.number().randomNumber())
					.description(faker.yoda().quote())
					.recurrency(RecurrencyEnum.MONTHLY)
					.build();
		}
	}
	
	public static class Expense {
		
		public static ExpensePO random() {
			BigDecimal fakeNumber = new BigDecimal(faker.numerify("##.##"));
			Date fakeDate = faker.date().past(30, TimeUnit.DAYS);
			
			return ExpensePO.builder()
					.value(fakeNumber)
					.id(faker.number().randomNumber())
					.recurrency(RecurrencyEnum.MONTHLY)
					.description(faker.yoda().quote())
					.date(DateHelper.toLocalDate(fakeDate))
					.build();
		}
		
	}
}
