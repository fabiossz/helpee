package br.org.iscmsp.helpee.model.dto.output.specification;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.org.iscmsp.helpee.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FuncionarioPesquisaSpec {

	private String nome;
	private String cpf;
	private String drt;

	public Specification<Funcionario> findAllFuncByNomeCpfDrt() {
		return (root, criteriaQuery, criteriaBuilder) -> {
		
			ArrayList<Predicate> predicates = new ArrayList<>();

			if (nome != null && !nome.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("nome"), nome + "%"));

			}

			if (cpf != null && !cpf.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("cpf"), cpf));
			}

			if (drt != null && !drt.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("drt"), drt));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		};
	}

}
