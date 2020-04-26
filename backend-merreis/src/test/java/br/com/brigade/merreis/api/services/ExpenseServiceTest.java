package br.com.brigade.merreis.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

import br.com.brigade.merreis.api.ExpenseSpecification;
import br.com.brigade.merreis.api.converters.ExpenseConverter;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;
import br.com.brigade.merreis.api.transfers.ExpenseOutputDTO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;
import br.com.brigade.merreis.common.helpers.Lorem;
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
	
	@Test
	public void it_should_execute_bean_validation_at_update() {
		exception.expect(ConstraintViolationException.class);
		ExpenseRequestDTO wrongExpense = ExpenseRequestDTO.builder().build();
		service.update(wrongExpense);
	}
	
	@Test
	public void it_should_clone_properties_between_expenses() {
		ExpensePO originExpense = Lorem.Expense.random();
		ExpensePO destinationExpense = ExpensePO.builder().build();
		service.cloneProperties(originExpense, destinationExpense);
		assertEquals(destinationExpense, originExpense);
		assertEquals(destinationExpense.getDescription(), originExpense.getDescription());
		assertEquals(destinationExpense.getDate(), originExpense.getDate());
		assertEquals(destinationExpense.getValue(), originExpense.getValue());
	}
	
	@Test
	public void it_should_return_changed_expense_at_update() {
		
		ExpensePO expectedExpense = Lorem.Expense.random();
		
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpensePO()));
		when(expenseConverter.toEntity(Mockito.any())).thenReturn(expectedExpense);
		when(repository.save(expectedExpense)).thenReturn(expectedExpense);
		
		ExpenseRequestDTO inputExpense = Lorem.ExpenseRequest.random();
		ExpensePO outputExpense = service.update(inputExpense);
		assertNotNull(outputExpense);
		assertEquals(outputExpense, expectedExpense);
	}
	
	@Test
	public void it_should_return_list_expenses_outputs_dto() {
		
		ExpenseOutputDTO outputExpense = Lorem.ExpenseOutput.random();
		List<ExpensePO> foundExpenses = Arrays.asList(Lorem.Expense.random());
		
		when(repository.findAll(ExpenseSpecification.dateGreaterEqualThan(Mockito.any()))).thenReturn(foundExpenses);
		when(expenseConverter.toOutput(Mockito.any())).thenReturn(outputExpense);
		
		List<ExpenseOutputDTO> currentExpenses = service.getAllExpensesCurrentMonth();
		assertNotNull(currentExpenses);
		assertThat(currentExpenses).anyMatch(expense -> Objects.equals(expense.getDescription(), outputExpense.getDescription()));
	}
}
