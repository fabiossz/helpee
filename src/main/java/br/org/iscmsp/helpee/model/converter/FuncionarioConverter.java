package br.org.iscmsp.helpee.model.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.iscmsp.helpee.model.Cargo;
import br.org.iscmsp.helpee.model.Departamento;
import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.Setor;
import br.org.iscmsp.helpee.model.dto.input.FuncionarioDTO;
import br.org.iscmsp.helpee.repository.CargoRepository;
import br.org.iscmsp.helpee.repository.DepartamentoRepository;
import br.org.iscmsp.helpee.repository.SetorRepository;

@Component
public class FuncionarioConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private SetorRepository setorRepository;

	public Funcionario converterDtoForEntity(FuncionarioDTO funcionarioDTO) {

		Cargo cargo = this.cargoRepository.findById(funcionarioDTO.getCargo()).get();
		Departamento departamento = this.departamentoRepository.findById(funcionarioDTO.getDepartamento()).get();
		Setor setor = this.setorRepository.findById(funcionarioDTO.getSetor()).get();

		Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);

		funcionario.setCargo(cargo);
		funcionario.setDepartamento(departamento);
		
		setor.setDepartamento(departamento);

		return funcionario;
	}

}
