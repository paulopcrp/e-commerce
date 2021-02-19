package com.github.paulocesar.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.paulocesar.domain.Pedido;
import com.github.paulocesar.repositories.PedidoRepository;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio; //objeto repositorio
	
		public Pedido buscar(Integer id) {
			 Optional<Pedido> obj = repositorio.findById(id);
			 return obj.orElseThrow(() -> new ObjectNotFoundException(
			  "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
			
		}
	
}

