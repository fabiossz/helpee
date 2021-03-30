package br.org.iscmsp.helpee.model.input.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

	@NotBlank(message = "Logradouro é obrigatório.")
	@Size(min = 1, max = 120, message = "Logradouro deve conter entre {min} e {max} caracteres.")
	private String logradouroEndereco;

	@NotBlank(message = "Número é obrigatório.")
	@Size(min = 1, max = 5, message = "Número do Endereço deve conter entre {min} e {max} caracteres.")
	private String numeroEndereco;

	@NotBlank(message = "Complemento é obrigatório.")
	@Size(min = 1, max = 60, message = "Complemento do Endereço deve conter entre {min} e {max} caracteres.")
	private String complementoEndereco;

	@NotBlank(message = "Bairro é obrigatório.")
	@Size(min = 1, max = 60, message = "Bairro deve conter entre {min} e {max} caracteres.")
	private String bairroEndereco;

	@Column(nullable = false, length = 40)
	@NotBlank(message = "Cidade é obrigatório.")
	@Size(min = 1, max = 60, message = "Cidade deve conter entre {min} e {max} caracteres.")
	private String cidadeEndereco;
	
	@NotBlank(message = "Estado é obrigatório.")
	@Size(min = 2, max = 2, message = "Estado deve conter {max} caracteres.")	
	private String estadoEndereco;

	@NotBlank(message = "CEP é obrigatório.")
	@Pattern(regexp = "\\d{5}\\d{3}", message = "CEP deve estar no formato 00000000")
	private String cepEndereco;

	@NotNull(message = "Departamento é obrigatório.")
	@Pattern(regexp = "\\d", message = "Departamento inválido.")
	private String departamentoId;

	@NotEmpty(message = "Setor é obrigatório.")
	private String setoresDepartamento;
	
	@NotEmpty(message = "Cargo é obrigatório.")	
	private String idCargo;
	
	

}
