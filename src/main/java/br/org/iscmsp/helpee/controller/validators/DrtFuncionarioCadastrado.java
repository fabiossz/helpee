package br.org.iscmsp.helpee.controller.validators;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.FuncionarioRepository;

public class DrtFuncionarioCadastrado implements Validator {

	private FuncionarioRepository funcionarioRepository;

	public DrtFuncionarioCadastrado(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;
		Optional<Funcionario> drt = this.funcionarioRepository.findByDrt(funcionarioDTO.getDrt());
		
		if(drt.isPresent()) {
			errors.rejectValue("drt", null, "DRT do Funcionário já cadastrado.");
		}
		
		
	}	
		

}
