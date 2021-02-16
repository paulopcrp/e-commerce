package com.github.paulocesar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.paulocesar.domain.Categoria;
import com.github.paulocesar.repositories.CategoriaRepository;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Exames Laboratoriais");
		Categoria cat2 = new Categoria(null, "Exames de Imagens");
		Categoria cat3 = new Categoria(null, "Exames Covid-19");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3)); 
	}

}
