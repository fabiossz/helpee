package br.org.iscmsp.helpee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.iscmsp.helpee.model.Setor;
import br.org.iscmsp.helpee.service.SetorService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private SetorService setorService;

	@PostMapping("/setores")
	@ResponseBody
	public ResponseEntity<?> findSetorByIdDepartamento(@RequestBody String codigo) {		

		List<Setor> setores = this.setorService.findSetoresByIdDepartamento(codigo);

		return ResponseEntity.ok(setores);
	}

}
