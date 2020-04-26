package br.com.brigade.merreis.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.transfers.ExpenseOutputDTO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;
import br.com.brigade.merreis.common.helpers.DateHelper;

@Component
public class ExpenseConverter {

	@Autowired
	private ModelMapper mapper;

	public ExpensePO toEntity(ExpenseRequestDTO source) {
		ExpensePO destination = mapper.map(source, ExpensePO.class);
		destination.setDate(DateHelper.toLocalDate(source.getDate()));
		return destination;
	}

	public ExpenseOutputDTO toOutput(ExpensePO source) {
		ExpenseOutputDTO destination = mapper.map(source, ExpenseOutputDTO.class);
		destination.setDate(DateHelper.toString(source.getDate()));
		return destination;
	}
}
