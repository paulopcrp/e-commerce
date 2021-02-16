package com.github.paulocesar.services.excessao;

public class ObjetoNaoEcontradoExcessao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEcontradoExcessao(String msg) {
		super(msg);
	}
	
	public ObjetoNaoEcontradoExcessao(String msg, Throwable cause) {
		super(msg, cause);
	}

}
