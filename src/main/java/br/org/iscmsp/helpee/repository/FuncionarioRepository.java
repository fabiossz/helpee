package br.org.iscmsp.helpee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.iscmsp.helpee.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {
	
	Optional<Funcionario> findByEmail(String email);
	
	Optional<Funcionario> findByCpf(String cpf);
	
	Optional<Funcionario> findByNome(String nome);
	
	Optional<Funcionario> findByDrt(String drt);

}
