package br.com.brigade.merreis.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@Entity
@Table(name = "TB_EXPENSE")
public class ExpensePO extends BasePO {

	private static final long serialVersionUID = 7937003960862375662L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DATE")
	private LocalDate date;
	
	@Column(name = "VALUE")
	private BigDecimal value;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "RECURRENCY")
	@Enumerated(EnumType.STRING)
	private RecurrencyEnum recurrency;
	
}
