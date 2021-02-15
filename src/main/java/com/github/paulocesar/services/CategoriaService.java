package com.github.paulocesar.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio; //objeto repositorio
	
		public Categoria buscar(Integer id) {
			Optional<Categoria> obj = repositorio.findById(id);
			return obj.orElse(null);
		}
	
		
		
		
	}

