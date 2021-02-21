package com.github.paulocesar.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.repositories.CategoriaRepository;
import com.github.paulocesar.services.excessao.DataIntegrityException;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio; //objeto repositorio
	
		public Categoria buscar(Integer id) {
			 Optional<Categoria> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjectNotFoundException(
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
		
		public void delete(Integer id) {
			buscar(id);
			try {
				repositorio.deleteById(id);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir Catertorias com produtos cadastrados");
			}
		}
		
		public List<Categoria> findAll() {
			return repositorio.findAll();
		}
		
		public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repositorio.findAll(pageRequest);
		}
	
}

