package br.org.iscmsp.helpee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.iscmsp.helpee.model.Setor;
import br.org.iscmsp.helpee.repository.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository setorRepository;	
	
	public List<Setor> findSetoresByIdDepartamento(String idDepartamento) {
	
		List<Setor> setores = this.setorRepository.findSetoresByIdDepartamento(idDepartamento);
		
		return setores;
	}

}
