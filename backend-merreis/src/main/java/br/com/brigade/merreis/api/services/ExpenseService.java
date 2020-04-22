package br.com.brigade.merreis.api.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.brigade.merreis.api.converters.ExpenseConverter;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;

@Service
@Validated
public class ExpenseService extends BaseService {
	
	@Autowired
	private ExpenseRepository repository;
	
	@Autowired
	private ExpenseConverter expenseConverter;

	public ExpensePO findById(Long id) {
		Optional<ExpensePO> foundExpense = repository.findById(id);
		return foundExpense.orElseThrow(() -> new RuntimeException());
	}

	public ExpensePO create(@Valid ExpenseRequestDTO inputExpense) {
		ExpensePO expense = expenseConverter.toEntity(inputExpense);
		return repository.save(expense);
	}
}
