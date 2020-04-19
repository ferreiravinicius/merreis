package br.com.brigade.merreis.api.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@MappedSuperclass
public class BasePO implements Serializable {

	private static final long serialVersionUID = 3865556568624089548L;
	
	@Transient
	private Long id;

}
