package br.com.brigade.merreis.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.common.helpers.TestingHelper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseRepositoryTest {

	@Autowired
	private ExpenseRepository repository;

	@Test
	public void shouldWireRepository() {
		assertNotNull(repository);
	}
	
	@Test
	public void it_should_persist_expense_at_save() {
		ExpensePO expense = TestingHelper.createExpenseValidated();
		ExpensePO savedExpense = repository.save(expense);
		assertNotNull(savedExpense.getId());
	}
	
	@Test
	public void it_should_return_list_of_expenses_at_find_by_description() {
		final String description = "Description to be found";
		ExpensePO expense = TestingHelper.createExpenseValidated();
		expense.setDescription(description);
		ExpensePO savedExpense = repository.save(expense);
		Set<ExpensePO> foundExpenses = repository.findByDescription(description);
		assertThat(foundExpenses).isNotEmpty();
		assertThat(foundExpenses).contains(savedExpense);
	}
	
	@Test
	public void it_should_return_empty_list_when_not_found_any_expense_at_find_by_description() {
		final String description = "ThisDescriptionShouldNotExistsAtAllm4t3";
		Set<ExpensePO> expenses = repository.findByDescription(description);
		assertThat(expenses).isEmpty();
	}
	
	
}
