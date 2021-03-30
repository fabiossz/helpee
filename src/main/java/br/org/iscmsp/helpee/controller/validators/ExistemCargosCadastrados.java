package br.org.iscmsp.helpee.controller.validators;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Cargo;
import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.CargoRepository;

public class ExistemCargosCadastrados implements Validator {

	private CargoRepository cargoRepository;

	public ExistemCargosCadastrados(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		List<Cargo> cargos = this.cargoRepository.findAll();		
		
		if (cargos.isEmpty()) {
			errors.rejectValue("idCargo", null, "Não existem cargos cadastrados.");
		}

	}

}
