package br.com.brigade.merreis.api.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brigade.merreis.MerreisConfiguration;
import br.com.brigade.merreis.api.models.ExpensePO;
import br.com.brigade.merreis.api.transfers.ExpenseRequestDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ExpenseConverter.class, MerreisConfiguration.class })
public class ExpenseConverterTest {
	
	@Autowired
	private ExpenseConverter converter;
	
	@Test
	public void shouldWireConverterComponent() {
		assertNotNull(converter);
	}
	
	@Test
	public void shouldConvertRequestIntoEntity() {
		
		final String dateText = "20/09/1994";
		
		final ExpenseRequestDTO dto = ExpenseRequestDTO.builder()
				.id(Mockito.anyLong())
				.date(dateText)
				.description(Mockito.anyString())
				.recurrency(Mockito.any())
				.build();
		
		ExpensePO entity = converter.toEntity(dto);

		assertNotNull(entity);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(LocalDate.of(1994, 9, 20), entity.getDate());
		assertEquals(dto.getRecurrency(), entity.getRecurrency());
		assertEquals(dto.getDescription(), entity.getDescription());
	}
	
}
