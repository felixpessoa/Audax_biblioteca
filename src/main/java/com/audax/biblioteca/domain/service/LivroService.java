package com.audax.biblioteca.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.repository.LivroRepository;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.dto.LivroDTO;

@Service
public class LivroService {

	private LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}

	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Livro create(LivroDTO obj) {
		Livro livro = fromDTO(obj);
		boolean findNome = findByName(livro);

		if (findNome) {
			throw new ObjectNotFoundException("Livro Já cadastrado");
		}
		return livroRepository.save(livro);
	}

	public Livro update(@Valid LivroDTO obj) {
		return create(obj);
	}

	public void delete(Integer id) {
		Livro livro = findById(id);
		livroRepository.deleteById(id);
	}


	private boolean findByName(Livro livro) {
		boolean existeNome = false;
		Optional<Livro> optional = livroRepository.findByNome(livro.getNome());
		if (optional.isPresent()) {
			if (!optional.get().getId().equals(livro.getId())) {
				existeNome = true;
			}
		}
		return existeNome;
	}

	private Livro fromDTO(LivroDTO obj) {
		Livro newObj = new Livro();
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		return newObj;
	}

}