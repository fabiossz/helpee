package br.org.iscmsp.helpee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "setor")
@Getter
@Setter
@ToString
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false, length = 30)
	@NotBlank(message = "Nome do Setor é obrigatório!")
	@Size(min = 1, max = 60, message = "O Nome do Setor deve conter entre {min} e {max} caracteres")
	private String descricao;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "Centro de Custos é obrigatório!")
	@Size(min = 1, max = 20, message = "Centro de Custos deve conter entre {min} e {max} caracteres")
	private String centro_custos;

	@Column(nullable = false, length = 8)
	@NotBlank(message = "Ramal é obrigatório")
	@Size(min = 4, max = 8, message = "Telefone do Setor deve conter entre {min} e {max} caracteres")
	private String ramal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento_fk")
	@JsonIgnore
	private Departamento departamento;

	@Deprecated
	protected Setor() {
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
		Setor other = (Setor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
