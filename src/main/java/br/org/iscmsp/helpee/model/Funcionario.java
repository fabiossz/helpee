package br.org.iscmsp.helpee.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@ToString
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false, length = 60, unique = true)
	private String nome;

	@Column(nullable = false, length = 8, unique = true)
	private String drt;

	@Column(nullable = false, length = 11, unique = true)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 9)
	private Genero genero;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 14)
	private EstadoCivil estado_civil;

	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate data_nascimento;

	@Column(nullable = false, length = 60)
	private String email;

	@Column(nullable = true, length = 10)
	private String telefone_residencial;

	@Column(nullable = false, length = 11)
	private String telefone_celular;

	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate data_admissao;

	@Column(nullable = true, columnDefinition = "DATE")
	private LocalDate data_demissao;

	@ManyToOne
	@JoinColumn(name = "id_cargo_fk")
	private Cargo cargo;

	@OneToOne
	@JoinColumn(name = "id_endereco_fk")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;

	@Deprecated
	public Funcionario() {
	}

	@PrePersist
	public void removeMaskInput() {
		this.cpf = this.cpf.replaceAll("[^0-9]+", "");
		this.telefone_celular = this.telefone_celular.replaceAll("[^0-9]+", "");

		if (!this.telefone_residencial.isEmpty()) {
			this.telefone_residencial = this.telefone_residencial.replaceAll("[^0-9]+", "");
		}

		this.nome = this.nome.toUpperCase();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
