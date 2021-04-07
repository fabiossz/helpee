package br.org.iscmsp.helpee.controller.validators;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.dto.input.FuncionarioDTO;

public class DataAdmissaoAnteriorDataAtual implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		try {

			FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;
			boolean dataAdmissaoPosterior = funcionarioDTO.getData_admissao().isAfter(LocalDate.now());

			if (dataAdmissaoPosterior && funcionarioDTO.getData_admissao() != null) {
				errors.rejectValue("data_admissao", null, "Data de Admissão não deve ser superior a data atual.");
			}

		} catch (Exception e) {
			errors.rejectValue("data_admissao", null, "Data de Admissão inválida.");
		}

	}

}
