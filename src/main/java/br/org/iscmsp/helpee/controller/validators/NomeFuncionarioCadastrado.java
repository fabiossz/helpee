package br.org.iscmsp.helpee.controller.validators;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.FuncionarioRepository;

public class NomeFuncionarioCadastrado implements Validator {

	private FuncionarioRepository funcionarioRepository;

	public NomeFuncionarioCadastrado(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;
		Optional<Funcionario> nome = this.funcionarioRepository.findByNome(funcionarioDTO.getNome());
		
		if(nome.isPresent()) {
			errors.rejectValue("nome", null, "Nome do Funcionário já cadastrado.");
		}
		
		
	}	
		

}
