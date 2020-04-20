package br.com.brigade.merreis.api.transfers;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.brigade.merreis.api.enums.RecurrencyEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ExpenseRequestDTO {
	
	private Long id;
	
	@Min(3)
	private String description;
	
	@NotNull
	private String date;
	
	@NotEmpty
	private String value;
	
	@NotNull
	private RecurrencyEnum recurrency;
	
}
