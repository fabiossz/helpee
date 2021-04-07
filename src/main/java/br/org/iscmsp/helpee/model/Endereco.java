package br.org.iscmsp.helpee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@ToString
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false, length = 80)
	@NotBlank(message = "Logradouro é obrigatório.")
	@Size(min = 1, max = 120, message = "Logradouro deve conter entre {min} e {max} caracteres.")
	private String logradouro;

	@Column(nullable = false, length = 10)
	@NotBlank(message = "Número é obrigatório.")
	@Size(min = 1, max = 10, message = "Número do Endereço deve conter entre {min} e {max} caracteres.")
	private String numero;

	@Column(nullable = false, length = 10)
	@NotBlank(message = "Complemento é obrigatório.")
	@Size(min = 1, max = 60, message = "Complemento do Endereço deve conter entre {min} e {max} caracteres.")
	private String complemento;

	@Column(nullable = false, length = 20)
	@NotBlank(message = "Bairro é obrigatório.")
	@Size(min = 1, max = 60, message = "Bairro deve conter entre {min} e {max} caracteres.")
	private String bairro;

	@Column(nullable = false, length = 40)
	@NotBlank(message = "Cidade é obrigatório.")
	@Size(min = 1, max = 60, message = "Cidade deve conter entre {min} e {max} caracteres.")
	private String cidade;

	@Column(nullable = false, length = 20)
	@NotBlank(message = "Estado é obrigatório.")
	@Size(min = 2, max = 60, message = "Estado deve conter entre {min} e {max} caracteres.")
	private String estado;

	@Column(nullable = false, length = 8)
	@NotBlank(message = "CEP é obrigatório.")
	@Pattern(regexp = "\\d{5}\\d{3}", message = "CEP deve estar no formato 00000000")
	private String cep;

	@Deprecated
	protected Endereco() {
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
