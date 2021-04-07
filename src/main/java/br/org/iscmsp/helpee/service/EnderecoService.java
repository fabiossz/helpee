package br.org.iscmsp.helpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.iscmsp.helpee.model.Endereco;
import br.org.iscmsp.helpee.repository.EnderecoRespository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRespository enderecoRespository;
	
	@Transactional
	public void salvarEndereco(Endereco endereco) {
		this.enderecoRespository.save(endereco);
	}

}
