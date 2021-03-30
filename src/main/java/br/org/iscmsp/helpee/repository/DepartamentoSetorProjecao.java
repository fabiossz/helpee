package br.org.iscmsp.helpee.repository;

public interface DepartamentoSetorProjecao {
	
	Long getIdDepartamento();
	String getDescricaoDepartamento();
	Long getIdSetor();
	String getDescricaoSetor();
	Long getSetorDepartamentoFk();

}
