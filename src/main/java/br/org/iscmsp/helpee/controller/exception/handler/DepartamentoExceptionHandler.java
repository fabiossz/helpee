package br.org.iscmsp.helpee.controller.exception.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "br.org.iscmsp.helpee.controller")
public class DepartamentoExceptionHandler {	
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<StandardError> handleNumberFormatException(NumberFormatException exception, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now());			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);		
	}

}
