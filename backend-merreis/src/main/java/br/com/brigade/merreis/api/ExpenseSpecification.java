package br.com.brigade.merreis.api;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.models.ExpensePO_;

public class ExpenseSpecification {

	public static Specification<ExpensePO> descriptionContaining(String text) {
		return (Root<ExpensePO> expense, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			if (StringUtils.isNotEmpty(text)) {
				String textUpper = "%" + text.toUpperCase() + "%";
				Expression<String> descriptionUpper = cb.upper(expense.get(ExpensePO_.description));
				return cb.like(descriptionUpper, textUpper);
			}
			return cb.conjunction();
		};
	}

	public static Specification<ExpensePO> dateGreaterEqualThan(LocalDate date) {
		return (Root<ExpensePO> expense, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			if (Objects.nonNull(date)) {
				Path<LocalDate> datePersisted = expense.get(ExpensePO_.date);
				return cb.greaterThanOrEqualTo(datePersisted, date);
			}
			return cb.conjunction();
		};
	}
}
