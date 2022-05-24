package com.audax.biblioteca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.repository.BibliotecaRepository;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.dto.LivroDTO;

@Service
public class BibliotecaService {
	
	private BibliotecaRepository bibliotecaRepository;

	public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
		super();
		this.bibliotecaRepository = bibliotecaRepository;
	}
	
	public Biblioteca findById(Integer id) {
		Optional<Biblioteca> obj = bibliotecaRepository.findById(id);
		return obj .orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Biblioteca.class.getName()));
	}
	
	public List<Biblioteca> findAll() {
		return bibliotecaRepository.findAll();
	}
	
	public Biblioteca create(BibliotecaDTO obj) {
		Biblioteca biblioteca = fromDTO(obj);
		boolean findNome = findByName(livro);

		if (findNome) {
			throw new ObjectNotFoundException("Livro Já cadastrado");
		}
		return bibliotecaRepository.save(livro);
	}
	

}
