package br.com.brigade.merreis.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;

@Service
public class ExpenseService extends BaseService {
	
	@Autowired
	private ExpenseRepository repository;

	public ExpensePO findById(Long id) {
		Optional<ExpensePO> foundExpense = repository.findById(id);
		return foundExpense.orElseThrow(() -> new RuntimeException());
	}
}
