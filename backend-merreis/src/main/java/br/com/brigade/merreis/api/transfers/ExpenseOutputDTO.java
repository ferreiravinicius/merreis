package br.com.brigade.merreis.api.transfers;

import java.math.BigDecimal;

import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ExpenseOutputDTO {
	
	private Long id;
	
	private String date;
	
	private BigDecimal value;
	
	private String description;
	
	private RecurrencyEnum recurrency;
}
