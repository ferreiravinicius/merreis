package br.com.brigade.merreis.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
	
	private ExpensePO create() {
		return TestingHelper.createExpenseValidated();
	}
	
	@Test
	public void shouldPersistExpenseWhenSave() {
		ExpensePO expense = create();
		ExpensePO savedExpense = repository.save(expense);
		assertNotNull(savedExpense.getId());
	}
	
	@Test
	public void shouldReturnListWithExpenseWhenFindByDescription() {
		final String description = Mockito.anyString();
		ExpensePO expense = create();
		expense.setDescription(description);
		ExpensePO savedExpense = repository.save(expense);
		Set<ExpensePO> foundExpenses = repository.findByDescription(description);
		assertThat(foundExpenses).isNotEmpty();
		assertThat(foundExpenses).contains(savedExpense);
	}
	
	@Test
	public void shouldReturnEmptyListWhenFindByDescriptionThatDoesNotExists() {
		final String description = Mockito.anyString();
		Set<ExpensePO> expenses = repository.findByDescription(description);
		assertThat(expenses).isEmpty();
	}
	
	
}
