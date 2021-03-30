package br.org.iscmsp.helpee.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.iscmsp.helpee.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query(value = "SELECT d FROM Departamento d " + "JOIN d.setores s")
	Set<Departamento> findDepartamentoBySetor();
	
	

}
