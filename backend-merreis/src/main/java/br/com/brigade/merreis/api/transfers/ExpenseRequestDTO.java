package br.com.brigade.merreis.api.transfers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
@EqualsAndHashCode
public class ExpenseRequestDTO {
	
	private Long id;
	
	@NotEmpty
	@Length(min = 3)
	private String description;
	
	@NotNull
	private String date;
	
	@NotEmpty
	private String value;
	
	@NotNull
	private RecurrencyEnum recurrency;
	
}
