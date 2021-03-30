package br.org.iscmsp.helpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	
	public void salvarFuncionario(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
	}	
	
}
