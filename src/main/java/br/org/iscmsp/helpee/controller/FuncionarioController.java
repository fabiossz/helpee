package br.org.iscmsp.helpee.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.org.iscmsp.helpee.controller.page.PageWrapper;
import br.org.iscmsp.helpee.controller.validators.CpfCadastrado;
import br.org.iscmsp.helpee.controller.validators.DataAdmissaoAnteriorDataAtual;
import br.org.iscmsp.helpee.controller.validators.DataNascimentoAnteriorDataAtual;
import br.org.iscmsp.helpee.controller.validators.DrtFuncionarioCadastrado;
import br.org.iscmsp.helpee.controller.validators.EmailDuplicado;
import br.org.iscmsp.helpee.controller.validators.ExisteCargoPeloID;
import br.org.iscmsp.helpee.controller.validators.ExistemCargosCadastrados;
import br.org.iscmsp.helpee.controller.validators.ExistemSetoresVinculadosDepartamentos;
import br.org.iscmsp.helpee.controller.validators.FuncionarioMaiorDeIdade;
import br.org.iscmsp.helpee.controller.validators.NomeFuncionarioCadastrado;
import br.org.iscmsp.helpee.model.Cargo;
import br.org.iscmsp.helpee.model.Departamento;
import br.org.iscmsp.helpee.model.EstadoCivil;
import br.org.iscmsp.helpee.model.Funcionario;
import br.org.iscmsp.helpee.model.Genero;
import br.org.iscmsp.helpee.model.converter.FuncionarioConverter;
import br.org.iscmsp.helpee.model.dto.input.FuncionarioDTO;
import br.org.iscmsp.helpee.model.dto.output.FuncionarioPesquisaDTO;
import br.org.iscmsp.helpee.model.dto.output.specification.FuncionarioPesquisaSpec;
import br.org.iscmsp.helpee.repository.CargoRepository;
import br.org.iscmsp.helpee.repository.DepartamentoRepository;
import br.org.iscmsp.helpee.repository.FuncionarioRepository;
import br.org.iscmsp.helpee.service.EnderecoService;
import br.org.iscmsp.helpee.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioConverter funcionarioConverter;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@InitBinder("funcionarioDTO")
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new DataAdmissaoAnteriorDataAtual(), new DataNascimentoAnteriorDataAtual(),
				new ExistemCargosCadastrados(cargoRepository),
				new ExistemSetoresVinculadosDepartamentos(departamentoRepository),
				new ExisteCargoPeloID(cargoRepository), new FuncionarioMaiorDeIdade(),
				new EmailDuplicado(funcionarioRepository), new CpfCadastrado(funcionarioRepository),
				new NomeFuncionarioCadastrado(funcionarioRepository),
				new DrtFuncionarioCadastrado(funcionarioRepository));

	}

	@GetMapping("/novo")
	public ModelAndView novo(FuncionarioDTO funcionarioDTO) {

		List<Cargo> cargos = this.cargoRepository.findAll();
		Set<Departamento> departamentosESetores = this.departamentoRepository.findDepartamentoBySetor();

		ModelAndView mv = new ModelAndView("funcionario/cadastro-funcionario");

		if (cargos.isEmpty()) {
			mv.addObject("messageErrors", "Não existem cargos cadastrados.");
			mv.addObject("disabled", "Cargo é obrigatório");
			return mv;
		}

		if (departamentosESetores.isEmpty()) {
			mv.addObject("messageErrors", "Não existem Departamentos cadastrados");
			mv.addObject("disabled", "Departamento é obrigatório");
			return mv;
		}

		mv.addObject("generos", Genero.values());
		mv.addObject("estados_civil", EstadoCivil.values());
		mv.addObject("cargos", cargos);
		mv.addObject("departamentos", departamentosESetores);

		return mv;
	}

	@PostMapping("/novo")
	@Transactional
	public ModelAndView cadastrar(@Valid FuncionarioDTO funcionarioDTO, BindingResult result, RedirectAttributes attr,
			HttpServletResponse response) {

		if (result.hasErrors()) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ModelAndView mv = this.novo(funcionarioDTO);
			return mv;
		}

		Funcionario funcionario = this.funcionarioConverter.converterDtoForEntity(funcionarioDTO);

		this.enderecoService.salvarEndereco(funcionarioDTO.getEndereco());

		this.funcionarioService.salvarFuncionario(funcionario);

		attr.addFlashAttribute("mensagem", "Funcionário Cadastrado com Sucesso!");

		return new ModelAndView("redirect:/funcionarios/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(FuncionarioPesquisaDTO funcionarioPesquisaDTO, BindingResult result,
			@PageableDefault(size = 1) Pageable pageable, HttpServletRequest request,
			FuncionarioPesquisaSpec funcionarioPesquisaSpec) {

		ModelAndView mv = new ModelAndView("funcionario/pesquisa-funcionario");

		Specification<Funcionario> specification = funcionarioPesquisaSpec.findAllFuncByNomeCpfDrt();

		Page<Funcionario> page = this.funcionarioRepository.findAll(specification, pageable);

		PageWrapper<Funcionario> pageWrapper = new PageWrapper<>(page, request);

		mv.addObject("page", pageWrapper);

		return mv;
	}

}
