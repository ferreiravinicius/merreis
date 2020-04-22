package br.com.brigade.merreis.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import br.com.brigade.merreis.api.converters.ExpenseConverter;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;
import br.com.brigade.merreis.common.helpers.TestingHelper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ExpenseService.class, MethodValidationPostProcessor.class })
public class ExpenseServiceTest {
	
	@Autowired
	private ExpenseService service;
	
	@MockBean
	private ExpenseRepository repository;
	
	@MockBean
	private ExpenseConverter expenseConverter;
	
	@Rule
    public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void it_should_autowire_service_and_repository() {
		assertNotNull(service);
		assertNotNull(repository);
	}
	
	@Test
	public void it_should_return_expense_at_find_by_id() {
		ExpensePO expenseReturned = ExpensePO.builder().build();
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(expenseReturned));
		ExpensePO expense = service.findById(Mockito.anyLong());
		assertNotNull(expense);
	}
	
	@Test
	public void it_should_throw_exception_when_not_found_at_find_by_id() {
		exception.expect(RuntimeException.class);
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		service.findById(Mockito.anyLong());
	}
	
	@Test
	public void it_should_execute_bean_validation_at_create() {
		exception.expect(ConstraintViolationException.class);
		ExpenseRequestDTO wrongExpense = ExpenseRequestDTO.builder().build();
		service.create(wrongExpense);
	}
	
	@Test
	public void it_should_return_expense_at_create() {
		
		ExpenseRequestDTO inputExpense = TestingHelper.createExpenseRequestValidated();
		ExpensePO outputExpense = TestingHelper.createExpenseValidated();
		
		when(expenseConverter.toEntity(inputExpense)).thenReturn(outputExpense);
		when(repository.save(outputExpense)).thenReturn(outputExpense);
		
		ExpensePO actualExpense = service.create(inputExpense);
		
		assertNotNull(actualExpense);
		assertThat(actualExpense.getDescription()).isEqualTo(outputExpense.getDescription());
	}
}
