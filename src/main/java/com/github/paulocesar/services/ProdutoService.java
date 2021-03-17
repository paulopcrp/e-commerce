package com.github.paulocesar.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.domain.Produto;
import com.github.paulocesar.repositories.CategoriaRepository;
import com.github.paulocesar.repositories.ProdutoRepository;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio; //objeto repositorio
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
		public Produto buscar(Integer id) {
			 Optional<Produto> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjectNotFoundException(
			  "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
			
		}
		
		public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			List<Categoria> categorias = categoriaRepository.findAllById(ids);
			return repositorio.search(nome, categorias, pageRequest);
			
		}
	
}

