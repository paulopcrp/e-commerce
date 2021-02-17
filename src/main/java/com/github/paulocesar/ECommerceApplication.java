package com.github.paulocesar;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.domain.Cidade;
import com.github.paulocesar.domain.Cliente;
import com.github.paulocesar.domain.Endereco;
import com.github.paulocesar.domain.Estado;
import com.github.paulocesar.domain.Pagamento;
import com.github.paulocesar.domain.PagamentoBoleto;
import com.github.paulocesar.domain.PagamentoCartao;
import com.github.paulocesar.domain.Pedido;
import com.github.paulocesar.domain.Produto;
import com.github.paulocesar.domain.enums.SituacaoPagamento;
import com.github.paulocesar.domain.enums.TipoCliente;
import com.github.paulocesar.repositories.CategoriaRepository;
import com.github.paulocesar.repositories.CidadeRepository;
import com.github.paulocesar.repositories.ClienteRepository;
import com.github.paulocesar.repositories.EnderecoRepository;
import com.github.paulocesar.repositories.EstadoRepository;
import com.github.paulocesar.repositories.PagamentoRepository;
import com.github.paulocesar.repositories.PedidoRepository;
import com.github.paulocesar.repositories.ProdutoRepository;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRespository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Exames Laboratoriais");
		Categoria cat2 = new Categoria(null, "Exames de Imagens");
		Categoria cat3 = new Categoria(null, "Exames Covid-19");
		
		Produto prd1 = new Produto(null, "Hemograma Total", 54.00);
		Produto prd2 = new Produto(null, "Hemograma Plaquetas", 35.00);
		Produto prd3 = new Produto(null, "Raio-X", 120.15);
		Produto prd4 = new Produto(null, "PCR", 250.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prd1, prd2));
		cat2.getProdutos().addAll(Arrays.asList(prd3));
		cat3.getProdutos().addAll(Arrays.asList(prd4));
		
		prd1.getCategorias().addAll(Arrays.asList(cat1));
		prd2.getCategorias().addAll(Arrays.asList(cat1));
		prd3.getCategorias().addAll(Arrays.asList(cat2));
		prd4.getCategorias().addAll(Arrays.asList(cat3));
		
		
		Estado est1 = new Estado(null, "Rio Grande do Sul");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Porto Alegre", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Caxias do Sul", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRespository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3)); 
		produtoRepository.saveAll(Arrays.asList(prd1, prd2, prd3, prd4));
		
		Cliente cli1 = new Cliente(null, "Paulo Cesar", "email@email.com", "72845821034", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("31981086644"));
		
		Endereco end1 = new Endereco(null, "Rua do Lago", "409", "casa", "São Marcos", "31920440", cli1, cid1 );
		Endereco end2 = new Endereco(null, "Rua Duque de Caxias", "409","apto 915", "São Pelegrino", "54990900", cli1, cid3 );
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("17/02/2021 18:10"), cli1, end2);
		Pedido ped2 = new Pedido(null, sdf.parse("17/02/2021 18:13"), cli1, end1);
		
		Pagamento pag1 = new PagamentoCartao(null, SituacaoPagamento.QUITADO, ped1, 3);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, SituacaoPagamento.PENDENTE, ped2, sdf.parse("17/02/2021 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		
	}

}
