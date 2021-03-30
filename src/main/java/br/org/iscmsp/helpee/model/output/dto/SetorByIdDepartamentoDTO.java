package br.org.iscmsp.helpee.model.output.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.org.iscmsp.helpee.model.Setor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SetorByIdDepartamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Setor> setores = new ArrayList();
	
	

}
