package br.com.brigade.merreis.api.specs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brigade.merreis.api.ExpenseSpecification;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;
import br.com.brigade.merreis.common.helpers.TestingHelper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseSpecificationTest {

	@Autowired
	private ExpenseRepository repository;

	private ExpensePO createWithDescription(String description) {
		ExpensePO expense = TestingHelper.createExpenseValidated();
		expense.setDescription(description);
		return expense;
	}
	
	private ExpensePO createWithDate(LocalDate date) {
		ExpensePO expense = TestingHelper.createExpenseValidated();
		expense.setDate(date);
		return expense;
	}
	
	@Test
	public void descriptionContaining_shouldReturnAllExpensesConainingText() {

		final String textArg = "dope";

		List<ExpensePO> expenses = Stream.of(
				createWithDescription("This description is dope"),
				createWithDescription("Another dope description"), 
				createWithDescription("Dope description in here"),
				createWithDescription("Super dOpE"), 
				createWithDescription("Nothing in here"))
				.collect(Collectors.toList());

		repository.saveAll(expenses);

		List<ExpensePO> foundExpenses = repository.findAll(ExpenseSpecification.descriptionContaining(textArg));
		assertNotNull(foundExpenses);
		assertThat(foundExpenses).hasSize(4);
		
	}
	
	@Test
	public void dateGreaterEqualThan_shouldReturnAllExpensesGreaterEqualSpecificDate() {
		
		LocalDate september20 = LocalDate.of(2020, Month.SEPTEMBER, 5);
		LocalDate september15 = LocalDate.of(2020, Month.SEPTEMBER, 15);
		LocalDate september25 = LocalDate.of(2020, Month.SEPTEMBER, 25);
		
		List<ExpensePO> expenses = Stream.of(
				createWithDate(september20),
				createWithDate(september15),
				createWithDate(september25))
				.collect(Collectors.toList());
		
		repository.saveAll(expenses);
		
		LocalDate dateFilter = LocalDate.of(2020, Month.SEPTEMBER, 15);
		List<ExpensePO> foundExpenses = repository.findAll(ExpenseSpecification.dateGreaterEqualThan(dateFilter));
		assertNotNull(foundExpenses);
		assertThat(foundExpenses).hasSize(2);
	}

}
