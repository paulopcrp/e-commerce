package com.github.paulocesar.resources.excessao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.paulocesar.services.excessao.ObjetoNaoEcontradoExcessao;

@ControllerAdvice
public class ExcessaoDeRecursoHandler {
	
	@ExceptionHandler(ObjetoNaoEcontradoExcessao.class)
	public ResponseEntity<ErroPadrao> ObjetoNaoEcontrado(ObjetoNaoEcontradoExcessao e, HttpServletRequest requisicao) {
		
		ErroPadrao err = new ErroPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
