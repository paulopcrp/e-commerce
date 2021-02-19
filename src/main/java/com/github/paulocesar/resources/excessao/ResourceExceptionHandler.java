package com.github.paulocesar.resources.excessao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.github.paulocesar.services.excessao.DataIntegrityException;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> ObjetoNaoEcontrado(ObjectNotFoundException e, HttpServletRequest requisicao) {
		
		StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandartError> erroIntegridade(DataIntegrityException e, HttpServletRequest requisicao) {
		
		StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
	

}
