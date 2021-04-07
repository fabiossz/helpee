package br.org.iscmsp.helpee.controller.validators;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.org.iscmsp.helpee.model.Cargo;
import br.org.iscmsp.helpee.model.dto.input.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.CargoRepository;

public class ExisteCargoPeloID implements Validator {

	private CargoRepository cargoRepository;

	public ExisteCargoPeloID(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) { 
		return FuncionarioDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		try {
			FuncionarioDTO funcionarioDTO = (FuncionarioDTO) target;	
			Optional<Cargo> cargo = null;
			
			if(funcionarioDTO.getCargo() != null) {
				cargo = this.cargoRepository.findById(funcionarioDTO.getCargo());	
				
				if (!cargo.isPresent() || cargo.isEmpty()) {
					errors.rejectValue("idCargo", null, "Cargo não encontrado.");
				}
			}
			
		}catch(NumberFormatException | NullPointerException ex) {
			errors.rejectValue("idCargo", null, "Cargo inválido.");
		}	
		
	}

}
