package com.audax.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.model.Bibliotecario;
import com.audax.biblioteca.domain.model.Usuario;
import com.audax.biblioteca.domain.repository.BibliotecarioRepository;
import com.audax.biblioteca.domain.service.exception.DataIntegrityException;
import com.audax.biblioteca.domain.service.exception.ObjectNotFoundException;
import com.audax.biblioteca.domain.service.exception.UsuarioService;
import com.audax.biblioteca.dto.BibliotecarioDTO;

@Service
public class BibliotecarioService {
	
	private BibliotecarioRepository bibliotecarioRepository;
	private BibliotecaService bibliotecaService;
	private UsuarioService usuarioService;
	private PasswordEncoder passwordEncoder;

	public BibliotecarioService(BibliotecarioRepository bibliotecarioRepository, BibliotecaService bibliotecaService,
			UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
		super();
		this.bibliotecarioRepository = bibliotecarioRepository;
		this.bibliotecaService = bibliotecaService;
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Bibliotecario findById(Integer id) {
		Optional<Bibliotecario> obj = bibliotecarioRepository.findById(id);
		return obj .orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Bibliotecario.class.getName()));
	}
	
	public List<Bibliotecario> findAll() {
		return bibliotecarioRepository.findAll();
	}
	
	public Bibliotecario create(BibliotecarioDTO dto) {
		Bibliotecario obj = fromDTO(dto);
		if (obj.getId()== null) {
			obj.setDataCriacao(LocalDateTime.now());
			obj.setStatus("Desativado");
		}
		return bibliotecarioRepository.save(obj);
	}
	

	public Bibliotecario update(@Valid @PathVariable Integer id, Bibliotecario obj) {
		Optional<Bibliotecario> optObj  = bibliotecarioRepository.findById(id);
		if (optObj.isEmpty()) {
			throw new ObjectNotFoundException("Usuario não encontrado!");
		}
		String senhaCriptografada = passwordEncoder.encode(obj.getUsuario().getPassword());
		obj.getUsuario().setPassword(senhaCriptografada);
		obj.getUsuario().setUsuarioId(optObj.get().getUsuario().getUsuarioId());
		usuarioService.upDateUsuario(obj.getUsuario().getUsuarioId(), obj.getUsuario());
		obj.setId(id);
		obj.setDataCriacao(optObj.get().getDataCriacao());
		return bibliotecarioRepository.save(obj);
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
		String senhaCriptografada = passwordEncoder.encode(obj.getUsuario().getPassword());
		Usuario usuario = new Usuario(obj.getUsuario().getUsuarioId(), obj.getUsuario().getLogin(), senhaCriptografada, obj.getUsuario().getAdmin());
		newObj.setUsuario(usuario);
		return newObj;
	}
	

}
