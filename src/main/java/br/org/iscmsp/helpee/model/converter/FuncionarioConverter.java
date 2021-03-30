package br.org.iscmsp.helpee.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;

@Component
public class FuncionarioConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Funcionario converterDtoForEntity(FuncionarioDTO funcionarioDTO) {
		return modelMapper.map(funcionarioDTO, Funcionario.class);
	}

}
