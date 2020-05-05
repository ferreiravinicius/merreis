package br.com.brigade.merreis.api.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.brigade.merreis.api.ExpenseSpecification;
import br.com.brigade.merreis.api.converters.ExpenseConverter;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.repositories.ExpenseRepository;
import br.com.brigade.merreis.api.transfers.ExpenseOutputDTO;
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
	
	public ExpensePO update(@Valid ExpenseRequestDTO inputExpense) {
		ExpensePO expensePersisted = findById(inputExpense.getId());
		ExpensePO changedExpense = expenseConverter.toEntity(inputExpense);
		cloneProperties(changedExpense, expensePersisted);
		return repository.save(expensePersisted);
	}
	
	public void cloneProperties(ExpensePO originExpense, ExpensePO destinationExpense) {
		BeanUtils.copyProperties(originExpense, destinationExpense);
	}

	public List<ExpenseOutputDTO> getAllExpensesCurrentMonth() {
		LocalDate dateCurrent = LocalDate.now();
		return getAllExpensesSpecificYearMonth(dateCurrent.getYear(), dateCurrent.getMonth());
	}

	public List<ExpenseOutputDTO> getAllExpensesSpecificYearMonth(Integer year, Month month) {
		LocalDate dateWithFirstDayYearMonth = LocalDate.of(year, month, 1);
		Specification<ExpensePO> dateSpec = ExpenseSpecification.dateGreaterEqualThan(dateWithFirstDayYearMonth);
		List<ExpensePO> currentExpenses = repository.findAll(dateSpec); //TODO Sort.by(Direction.ASC, ExpensePO_.DATE)
		
		List<ExpenseOutputDTO> outputExpenses = currentExpenses.parallelStream()
				.map(expenseConverter::toOutput)
				.collect(Collectors.toList());
		
		return outputExpenses;
	}
	
}
