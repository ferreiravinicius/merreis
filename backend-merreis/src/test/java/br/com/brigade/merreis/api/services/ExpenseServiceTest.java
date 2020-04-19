package br.com.brigade.merreis.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ExpenseService.class })
public class ExpenseServiceTest {
	
	@Autowired
	private ExpenseService service;
	
	@MockBean
	private ExpenseRepository repository;
	
	@Test
	public void shouldWireServiceAndMockRepository() {
		assertNotNull(service);
		assertNotNull(repository);
	}
	
	@Test
	public void shouldReturnExpenseWhenFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpensePO()));
		ExpensePO expense = service.findById(Mockito.anyLong());
		assertNotNull(expense);
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenFindByIdReturnsNothing() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		service.findById(Mockito.anyLong());
	}

}
