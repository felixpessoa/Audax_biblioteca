package com.audax.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.repository.BibliotecaRepository;
import com.audax.biblioteca.domain.service.exception.DataIntegrityException;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.dto.BibliotecaDTO;

@Service
public class BibliotecaService {
	
	private BibliotecaRepository bibliotecaRepository;
	private LivroService livroService;

	@Lazy
	public BibliotecaService(
			BibliotecaRepository bibliotecaRepository, LivroService livroService) {
		super();
		this.bibliotecaRepository = bibliotecaRepository;
		this.livroService = livroService;
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
		List<Livro> livros = livroService.findAll();
		
		if (biblioteca.getId()== null) {
			biblioteca.setDataCadastro(LocalDateTime.now());
			biblioteca.getLivros().addAll(livros);
		}
		return bibliotecaRepository.save(biblioteca);
	}
	
	public Biblioteca update(@Valid @PathVariable Integer id, BibliotecaDTO obj) {
		Biblioteca biblioteca = findById(id);
		obj.setDataCadastro(biblioteca.getDataCadastro());
		obj.setId(id);
		return create(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			bibliotecaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	private Biblioteca fromDTO(BibliotecaDTO obj) {
		Biblioteca newObj = new Biblioteca();
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setDataCadastro(obj.getDataCadastro());
		
//		newObj.setLivros(obj.getLivros());
//		newObj.setBibliotecarios(obj.getBibliotecarios());
		return newObj;
	}
	

}
