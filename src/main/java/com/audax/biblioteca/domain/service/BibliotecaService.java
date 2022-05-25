package com.audax.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.repository.BibliotecaRepository;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.dto.BibliotecaDTO;

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
		if (biblioteca.getId()== null) {
			biblioteca.setDataCadastro(LocalDateTime.now());;
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
		Biblioteca biblioteca = findById(id);
		bibliotecaRepository.deleteById(id);
	}

	private Biblioteca fromDTO(BibliotecaDTO obj) {
		Biblioteca newObj = new Biblioteca();
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setDataCadastro(obj.getDataCadastro());
//		newObj.setLivros(obj.getLivros());
		newObj.setBibliotecarios(obj.getBibliotecarios());
		return newObj;
	}
	

}
