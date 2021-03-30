package br.org.iscmsp.helpee.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departamento")
@Getter
@Setter
@ToString
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false, length = 40, unique = true)
	@NotBlank(message = "Nome do Departamento é obrigatório!")
	@Size(min = 1, max = 60, message = "Nome do Departamento deve conter entre {min} e {max} caracteres!")
	private String descricao;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "Centro de Custo do Departamento é obrigatório!")
	@Size(min = 1, max = 60, message = "O Centro de Custos do Departamento deve conter entre {min} e {max} caracteres!")
	private String centro_custos;

	@OneToMany(mappedBy = "departamento")
	private List<Funcionario> funcionarios = new ArrayList<>();

	@OneToMany(mappedBy = "departamento")
	@NotEmpty(message = "Setor é obrigatório")
	private Set<Setor> setores = new HashSet<>();

	@Deprecated
	protected Departamento() {
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
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
