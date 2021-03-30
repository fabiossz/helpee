package br.org.iscmsp.helpee.controller.validators;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Departamento;
import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.DepartamentoRepository;

public class ExistemSetoresVinculadosDepartamentos implements Validator {

	private DepartamentoRepository departamentoRepository;

	public ExistemSetoresVinculadosDepartamentos(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Set<Departamento> setorByDepartamento = this.departamentoRepository.findDepartamentoBySetor();		

		if (setorByDepartamento.isEmpty()) {
			errors.rejectValue("departamentoId", null, "Não existe Setor vinculado ao Departamento.");
		}

	}

}
