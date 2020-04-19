package br.com.brigade.merreis.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecurrencyEnum {

	ONCE("Once"),
	DAILY("Daily"),
	MONTHLY("Monthly"),
	ANNUALLY("Annually");

	private String description;
}
