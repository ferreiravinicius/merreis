package br.com.brigade.merreis.api.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brigade.merreis.api.models.ExpensePO;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpensePO, Long> {

	public Set<ExpensePO> findByDescription(String description);

}
