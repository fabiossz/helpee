package br.org.iscmsp.helpee.controller.validators;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;

public class FuncionarioMaiorDeIdade implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		try {
			FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;

			LocalDate data_nascimento = funcionarioDTO.getData_nascimento();
			LocalDate data_atual = LocalDate.now();
			
			long idade = ChronoUnit.YEARS.between(data_nascimento, data_atual);

			if (idade < 18) {

				errors.rejectValue("data_nascimento", null, "Funcionário não pode ser menor de idade.");
			}
		}catch(Exception e) {
			errors.rejectValue("data_nascimento", null, "Verifique a Data de Nascimento digitada.");
		}
		
	}

}
