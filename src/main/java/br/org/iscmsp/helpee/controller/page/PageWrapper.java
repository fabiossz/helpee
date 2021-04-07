package br.org.iscmsp.helpee.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.iscmsp.helpee.model.Funcionario;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest request) {
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(request);
	}

	
	@SuppressWarnings("unchecked")
	public List<Funcionario> funcionarios() {	
		return (List<Funcionario>) this.page.getContent();		
	}
		
	public int totalPaginas() {
		return this.page.getTotalPages();
	}

	public boolean primeiraPagina() {
		return this.page.isFirst();
	}

	public boolean ultimaPagina() {
		return this.page.isLast();
	}

	public int paginaAtual() {
		return this.page.getNumber();
	}

	public String ordernacao(String ordenacao) {

		String defaultOrder = "asc";

		if (page.getSort().getOrderFor(ordenacao) != null) {
			Order order = page.getSort().getOrderFor(ordenacao);
			defaultOrder = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return defaultOrder;
	}

	public String urlPara(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String ordernarPara(String order) {
		UriComponentsBuilder uriOrder = UriComponentsBuilder
				.fromUriString(this.uriBuilder.build(true).encode().toUriString());

		String sort = String.format("%s,%s", order, this.ordernacao(order));

		return uriOrder.replaceQueryParam("sort", sort).build(true).encode().toUriString();
	}		
	
	
	public boolean descendente(String property) {
		return this.ordernacao(property).equals("asc");
	}
	
	public boolean ordenada(String property) {
		return this.page.getSort().getOrderFor(property) != null ? true : false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
