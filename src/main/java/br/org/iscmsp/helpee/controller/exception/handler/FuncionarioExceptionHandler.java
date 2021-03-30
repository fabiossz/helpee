package br.org.iscmsp.helpee.controller.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.ConstraintViolationException;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.org.iscmsp.helpee.model.input.dto.FuncionarioDTO;

@ControllerAdvice(basePackages = "br.org.iscmsp.helpee.controller")
public class FuncionarioExceptionHandler {	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ DataIntegrityViolationException.class, DataException.class,
			SQLIntegrityConstraintViolationException.class, ConstraintViolationException.class })
	public ModelAndView DataIntegrityViolationException(RuntimeException ex) {
		ModelAndView mv = new ModelAndView("funcionario/cadastro-funcionario");	
		mv.addObject(new FuncionarioDTO());
		mv.addObject("messageErrors", "Não foi possível cadastrar o Funcionário.");
		return mv;
	}
}
