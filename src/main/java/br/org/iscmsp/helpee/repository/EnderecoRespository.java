package br.org.iscmsp.helpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.iscmsp.helpee.model.Endereco;

@Repository
public interface EnderecoRespository extends JpaRepository<Endereco, Long> {

}
