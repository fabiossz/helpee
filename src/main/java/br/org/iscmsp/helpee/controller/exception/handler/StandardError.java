package br.org.iscmsp.helpee.controller.exception.handler;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError {

	private Integer status;
	private String message;
	private LocalDateTime timeError;

	public StandardError(Integer status, String message, LocalDateTime timeError) {
		this.status = status;
		this.message = message;
		this.timeError = timeError;

	}

}
