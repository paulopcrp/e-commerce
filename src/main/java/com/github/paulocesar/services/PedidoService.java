package com.github.paulocesar.services;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.ItemPedido;
import com.github.paulocesar.domain.PagamentoBoleto;
import com.github.paulocesar.domain.Pedido;
import com.github.paulocesar.domain.enums.SituacaoPagamento;
import com.github.paulocesar.repositories.ItemPedidoRepository;
import com.github.paulocesar.repositories.PagamentoRepository;
import com.github.paulocesar.repositories.PedidoRepository;
import com.github.paulocesar.repositories.ProdutoRepository;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio; //objeto repositorio
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
		public Pedido buscar(Integer id) {
			 Optional<Pedido> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjectNotFoundException(
			  "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
			
		}
		
		public Pedido inserir(Pedido obj) {
			obj.setId(null);
			obj.setInstante(new Date());
			obj.getPagamento().setSituacaoPagamento(SituacaoPagamento.PENDENTE);
			obj.getPagamento().setPedido(obj);
			if (obj.getPagamento() instanceof PagamentoBoleto) {
				PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
				boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
			}
			obj = repositorio.save(obj);
			pagamentoepository.save(obj.getPagamento());
			for (ItemPedido ip: obj.getItens()) {
				ip.setDesconto(0.0);
				ip.setPreco(produtoRepository.findById(ip.getProduto().getId()).get().getPreco());
				ip.setPedido(obj);
				ip.setPedido(obj);
				
			}
			
			itemPedidoRepository.saveAll(obj.getItens());
			return obj;
		}
}

