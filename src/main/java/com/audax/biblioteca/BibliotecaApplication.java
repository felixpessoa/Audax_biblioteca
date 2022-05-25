package com.audax.biblioteca;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Bibliotecario;
import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.repository.BibliotecaRepository;
import com.audax.biblioteca.domain.repository.BibliotecarioRepository;
import com.audax.biblioteca.domain.repository.LivroRepository;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner{
//	public class BibliotecaApplication {
	
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		
		
	}

}
