package br.org.iscmsp.helpee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.iscmsp.helpee.model.Setor;

@Repository
public interface SetorRepository extends PagingAndSortingRepository<Setor, Long>, JpaSpecificationExecutor<Setor> {
	
	@Query("SELECT s FROM Setor s "
			+ "WHERE s.descricao = :setor")
	Optional<Setor> findSetorByName(String setor);

	@Query(value = "SELECT * FROM setor s "
			+ "INNER JOIN departamento d "
			+ "on d.id = s.id_departamento_fk "
			+ "WHERE d.id = :id", nativeQuery = true)
	List<Setor> findSetoresByIdDepartamento(@Param("id") String id);	
	
	Optional<Setor> findById(String id);

}
