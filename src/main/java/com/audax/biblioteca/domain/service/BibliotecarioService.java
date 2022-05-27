package com.audax.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Bibliotecario;
import com.audax.biblioteca.domain.repository.BibliotecarioRepository;
import com.audax.biblioteca.domain.service.exception.DataIntegrityException;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.dto.BibliotecarioDTO;

@Service
public class BibliotecarioService {
	
	private BibliotecarioRepository bibliotecarioRepository;
	private BibliotecaService bibliotecaService;

	public BibliotecarioService(BibliotecarioRepository bibliotecarioRepository, BibliotecaService bibliotecaService) {
		super();
		this.bibliotecarioRepository = bibliotecarioRepository;
		this.bibliotecaService = bibliotecaService;
	}
	
	public Bibliotecario findById(Integer id) {
		Optional<Bibliotecario> obj = bibliotecarioRepository.findById(id);
		return obj .orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Bibliotecario.class.getName()));
	}
	
	public List<Bibliotecario> findAll() {
		return bibliotecarioRepository.findAll();
	}
	
	public Bibliotecario create(BibliotecarioDTO obj) {
		Bibliotecario bibliotecario = fromDTO(obj);
		if (bibliotecario.getId()== null) {
			bibliotecario.setDataCriacao(LocalDateTime.now());
			bibliotecario.setStatus("A");
			bibliotecario.setAdmin(false);
		}
		
		return bibliotecarioRepository.save(bibliotecario);
	}
	

	public Bibliotecario update(@Valid @PathVariable Integer id, BibliotecarioDTO obj) {
		Bibliotecario bibliotecario = findById(id);
		obj.setDataCriacao(bibliotecario.getDataCriacao());
		obj.setId(id);
		return create(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			bibliotecarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	
	private Bibliotecario fromDTO(BibliotecarioDTO obj) {
		Bibliotecario newObj = new Bibliotecario();
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setDataCriacao(obj.getDataCriacao());
		Biblioteca biblioteca = bibliotecaService.findById(obj.getBibliotecas());
		newObj.getBibliotecas().add(biblioteca);
		newObj.setStatus(obj.getStatus());
		newObj.setAdmin(obj.getAdmin());
		return newObj;
	}
	

}
