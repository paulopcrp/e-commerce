package com.github.paulocesar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.domain.Cidade;
import com.github.paulocesar.domain.Estado;
import com.github.paulocesar.domain.Produto;
import com.github.paulocesar.repositories.CategoriaRepository;
import com.github.paulocesar.repositories.CidadeRepository;
import com.github.paulocesar.repositories.EstadoRepository;
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
		
	}

}
