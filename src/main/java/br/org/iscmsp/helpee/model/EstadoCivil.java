package br.org.iscmsp.helpee.model;

import lombok.Getter;

@Getter
public enum EstadoCivil {
	
	SOLTEIRO("Solteiro(a)"),
	CASADO("Casado(a)"),
	DIVORCIADO("Divorciado(a)"),
	VIUVO("Viúvo(a)"),
	SEPARADO("Separado(a)");

	private String descricao;

	EstadoCivil(String descricao) {
		this.descricao = descricao;
		
	}	
	
}
