package br.com.brigade.merreis.api.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brigade.merreis.MerreisConfiguration;
import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.transfers.ExpenseOutputDTO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;
import br.com.brigade.merreis.common.helpers.DateHelper;
import br.com.brigade.merreis.common.helpers.Lorem;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ExpenseConverter.class, MerreisConfiguration.class })
public class ExpenseConverterTest {
	
	@Autowired
	private ExpenseConverter converter;
	
	@Test
	public void it_should_autowire_converter() {
		assertNotNull(converter);
	}
	
	@Test
	public void it_should_convert_request_dto_into_entity() {
		
		final String dateText = "20/09/1994";
		final String valueText = "33.10";
		
		final ExpenseRequestDTO dto = ExpenseRequestDTO.builder()
				.date(dateText)
				.value(valueText)
				.id(123l)
				.recurrency(RecurrencyEnum.MONTHLY)
				.description("Expense description")
				.build();
		
		ExpensePO entity = converter.toEntity(dto);

		assertNotNull(entity);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(LocalDate.of(1994, 9, 20), entity.getDate());
		assertEquals(dto.getRecurrency(), entity.getRecurrency());
		assertEquals(dto.getDescription(), entity.getDescription());
		assertEquals(new BigDecimal(valueText), entity.getValue());
	}
	
	@Test
	public void it_should_convert_expense_entity_to_output_dto() {
		
		ExpensePO expense = Lorem.Expense.random();
		ExpenseOutputDTO outputExpense = converter.toOutput(expense);
		
		assertNotNull(outputExpense);
		assertEquals(expense.getId(), outputExpense.getId());
		assertEquals(expense.getDescription(), outputExpense.getDescription());
		assertEquals(expense.getValue(), outputExpense.getValue());
		assertEquals(expense.getRecurrency(), outputExpense.getRecurrency());
		LocalDate outputDate = DateHelper.toLocalDate(outputExpense.getDate());
		assertEquals(expense.getDate(), outputDate);
		
	}
}
