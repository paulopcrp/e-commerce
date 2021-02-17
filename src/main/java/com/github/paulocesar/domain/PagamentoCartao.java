package com.github.paulocesar.domain;

import javax.persistence.Entity;

import com.github.paulocesar.domain.enums.SituacaoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, SituacaoPagamento situacaoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, situacaoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
		
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	

}
