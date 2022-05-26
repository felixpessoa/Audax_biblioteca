package com.audax.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Bibliotecario;
import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.repository.BibliotecaRepository;
import com.audax.biblioteca.domain.repository.BibliotecarioRepository;
import com.audax.biblioteca.domain.repository.LivroRepository;

@Service
public class DBService {
	
	private BibliotecaRepository bibliotecaRepository;
	private BibliotecarioRepository bibliotecarioRepository;
	private LivroRepository livroRepository;
	
	public DBService(BibliotecaRepository bibliotecaRepository,
			BibliotecarioRepository bibliotecarioRepository,
			LivroRepository livroRepository) {
		super();
		this.bibliotecaRepository = bibliotecaRepository;
		this.bibliotecarioRepository = bibliotecarioRepository;
		this.livroRepository = livroRepository;
	}
	
	public void instantiateTestDatabase() {
		Biblioteca b1 = new Biblioteca(null, "Biblioteca Audax 01", LocalDateTime.of(2022, 05, 24, 11, 03));
		Biblioteca b2 = new Biblioteca(null, "Biblioteca Audax 02", LocalDateTime.of(2021, 01, 05, 00, 00));
		
		Bibliotecario f1 = new Bibliotecario(null, "Juca", LocalDateTime.of(2022, 05, 24, 11, 03), "S", false);
		Bibliotecario f2 = new Bibliotecario(null, "Jairo", LocalDateTime.of(2022, 06, 15, 20, 47), "S", false);
		Bibliotecario f3 = new Bibliotecario(null, "Zezinha", LocalDateTime.of(2022, 10, 20, 13, 53), "S", false);
		
		b1.getBibliotecarios().addAll(Arrays.asList(f1, f2));
		b2.getBibliotecarios().addAll(Arrays.asList(f1, f3));
		
		f1.getBibliotecas().addAll(Arrays.asList(b1, b2));
		f2.getBibliotecas().addAll(Arrays.asList(b1));
		f3.getBibliotecas().addAll(Arrays.asList(b2));
		
		
		
		
		Livro l1 = new Livro(null, "Harry Potter e a Pedra Filosofal");
		Livro l2 = new Livro(null, "Harry Potter e a Câmara Secreta");
		Livro l3 = new Livro(null, "Harry Potter e o Prisioneiro de Azkaban");
		Livro l4 = new Livro(null, "Harry Potter e o Cálice de Fogo");
		Livro l5 = new Livro(null, "Harry Potter e a Ordem da Fênix");
		Livro l6 = new Livro(null, "Harry Potter e o Enigma do Príncipe");
		
		b1.getLivros().addAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
		b2.getLivros().addAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
		
		l1.getBibliotecas().addAll(Arrays.asList(b1, b2));
		l2.getBibliotecas().addAll(Arrays.asList(b1, b2));
		l3.getBibliotecas().addAll(Arrays.asList(b1, b2));
		l4.getBibliotecas().addAll(Arrays.asList(b1, b2));
		l5.getBibliotecas().addAll(Arrays.asList(b1, b2));
		l6.getBibliotecas().addAll(Arrays.asList(b1, b2));
		
		bibliotecaRepository.saveAll(Arrays.asList(b1, b2));
		livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
		bibliotecarioRepository.saveAll(Arrays.asList(f1, f2, f3));
	}

}
