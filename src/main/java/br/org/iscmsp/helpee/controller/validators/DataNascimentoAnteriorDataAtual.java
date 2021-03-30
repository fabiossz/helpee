package br.org.iscmsp.helpee.controller.validators;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;

public class DataNascimentoAnteriorDataAtual implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		
		try {
			FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;
			boolean dataNscimentoPosterior = funcionarioDTO.getData_nascimento().isAfter(LocalDate.now());		
			
			if(dataNscimentoPosterior){
				errors.rejectValue("data_nascimento", null, "Data de Nascimento não deve ser superior a data atual.");				
			}
		}catch(Exception e) {
			errors.rejectValue("data_nascimento", null, "Data de Nascimento inválida.");	
		}
		
	}

}
