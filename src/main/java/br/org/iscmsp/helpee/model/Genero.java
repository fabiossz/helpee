package br.org.iscmsp.helpee.model;

import lombok.Getter;

@Getter
public enum Genero {

	FEMININO("Feminino"),
	MASCULINO("Masculino"),
	OUTROS("Outros");

	private String descricao;

	Genero(String descricao) {
		this.descricao = descricao;
	}

	
	

	



}
