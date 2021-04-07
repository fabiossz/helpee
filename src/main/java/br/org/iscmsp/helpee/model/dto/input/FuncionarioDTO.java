package br.org.iscmsp.helpee.model.dto.input;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.org.iscmsp.helpee.model.Endereco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuncionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Nome é obrigatório.")
	@Size(min = 1, max = 60, message = "Nome deve conter entre {min} e {max} caracteres.")
	private String nome;

	@NotBlank(message = "DRT é obrigatório.")
	@Size(min = 1, max = 10, message = "DRT deve conter entre {min} e {max} caracteres.")
	private String drt;

	@NotBlank(message = "CPF é obrigatório.")
	@CPF
	private String cpf;

	@NotEmpty(message = "Genêro é obrigatório.")
	private String genero;

	@NotEmpty(message = "Estado Civil é obrigatório.")
	private String estado_civil;

	@NotNull(message = "Data de Nascimento é obrigatório.")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_nascimento;

	@NotBlank(message = "E-mail é obrigatório.")
	@Email(message = "E-mail inválido.")
	private String email;

	private String telefone_residencial;

	@NotBlank(message = "Celular é obrigatório.")
	@Size(max = 14, message = "Telefone Celular deve conter {max} caracteres.")
	private String telefone_celular;

	@NotNull(message = "Data de Admissão é obrigatório.")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_admissao;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_demissao;

	@Valid
	@NotNull
	private Endereco endereco;

	@NotNull(message = "Departamento é obrigatório.")
	@Pattern(regexp = "\\d", message = "Departamento inválido.")
	private String departamento;

	@NotEmpty(message = "Setor é obrigatório.")
	private String setor;

	@NotEmpty(message = "Cargo é obrigatório.")
	private String cargo;

}

