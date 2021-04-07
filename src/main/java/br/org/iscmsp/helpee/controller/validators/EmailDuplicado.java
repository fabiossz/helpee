package br.org.iscmsp.helpee.controller.validators;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.dto.input.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.FuncionarioRepository;

public class EmailDuplicado implements Validator {
	
	private FuncionarioRepository funcionarioRepository;	

	public EmailDuplicado(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;
		Optional<Funcionario> email = this.funcionarioRepository.findByEmail(funcionarioDTO.getEmail());
		
		if(email.isPresent()) {
			errors.rejectValue("email", null, "E-mail já cadastrado, digite outro e-mail.");
		}
	}

}
