package br.org.iscmsp.helpee.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioPesquisaDTO {

	private String nome;
	private String drt;
	private String cpf;

	public FuncionarioPesquisaDTO(String nome, String drt, String cpf) {
		this.nome = nome;
		this.drt = drt;
		this.cpf = cpf;
	}

}
