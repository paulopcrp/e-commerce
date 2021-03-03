package com.github.paulocesar.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.github.paulocesar.DTO.ClienteDTO;
import com.github.paulocesar.DTO.ClienteNewDTO;
import com.github.paulocesar.domain.Cidade;
import com.github.paulocesar.domain.Cliente;
import com.github.paulocesar.domain.Endereco;
import com.github.paulocesar.domain.enums.TipoCliente;
import com.github.paulocesar.repositories.ClienteRepository;
import com.github.paulocesar.repositories.EnderecoRepository;
import com.github.paulocesar.services.excessao.DataIntegrityException;
import com.github.paulocesar.services.excessao.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio; //objeto repositorio
	
	@Autowired
	private EnderecoRepository enderecoRepository;

		public Cliente find(Integer id) {
			Optional<Cliente> obj = repositorio.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

			
		}
		
		public Cliente inserir(Cliente obj) {
			obj.setId(null);
			obj = repositorio.save(obj);
			enderecoRepository.saveAll(obj.getEnderecos());
			return obj;
		}
				
		public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repositorio.save(newObj);
		}
		
		public void delete(Integer id) {
			find(id);
			try {
				repositorio.deleteById(id);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir Clientes que já tem pedidos cadastrados");
			}
		}
		
		public List<Cliente> findAll() {
			return repositorio.findAll();
		}
		
		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repositorio.findAll(pageRequest);
		}
		
		public Cliente fromDTO(ClienteDTO objDTO) {
			return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
		}
		
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfCnpj(), TipoCliente.toEnum(objDto.getTipo()));
			Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDto.getTelefone1());
			//verifico se algum telefone esta preenchido
			if (objDto.getTelefone2() != null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3() != null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			return cli;
			
		}
		
		
		
		private void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}

}

