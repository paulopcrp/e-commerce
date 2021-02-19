package com.github.paulocesar.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.repositories.CategoriaRepository;
import com.github.paulocesar.services.excessao.ObjetoNaoEcontradoExcessao;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio; //objeto repositorio
	
		public Categoria buscar(Integer id) {
			 Optional<Categoria> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjetoNaoEcontradoExcessao(
			  "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
			
		}
		
		public Categoria inserir(Categoria obj) {
			obj.setId(null);
			return repositorio.save(obj);
		}
		
		public Categoria alterar(Categoria obj) {
			buscar(obj.getId());
			return repositorio.save(obj);
		}
	
}

