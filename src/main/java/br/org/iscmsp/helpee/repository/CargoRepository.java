package br.org.iscmsp.helpee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.iscmsp.helpee.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Optional<Cargo> findById(String id);
		

}
