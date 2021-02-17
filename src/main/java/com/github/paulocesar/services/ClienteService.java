package com.github.paulocesar.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Cliente;
import com.github.paulocesar.repositories.ClienteRepository;
import com.github.paulocesar.services.excessao.ObjetoNaoEcontradoExcessao;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio; //objeto repositorio
	
		public Cliente buscar(Integer id) {
			 Optional<Cliente> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjetoNaoEcontradoExcessao(
			  "Objeto não encontrado! Id: " + id + ", Cliente: " + Cliente.class.getName()));
			
		}
	
}

