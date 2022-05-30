package com.audax.biblioteca.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audax.biblioteca.domain.model.Bibliotecario;
import com.audax.biblioteca.domain.service.BibliotecarioService;
import com.audax.biblioteca.dto.BibliotecarioDTO;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/bibliotecarios")
public class BibliotecarioController {
	
	private BibliotecarioService bibliotecaService;

	public BibliotecarioController(BibliotecarioService bibliotecaService) {
		super();
		this.bibliotecaService = bibliotecaService;
	}
	
	@GetMapping
	public ResponseEntity<List<BibliotecarioDTO>> findAll(){
		List<Bibliotecario> list = bibliotecaService.findAll();
		List<BibliotecarioDTO> listDto = list.stream().map(obj -> new BibliotecarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Bibliotecario> findByIdBibliotecario(@PathVariable Integer id){
		Bibliotecario obj = bibliotecaService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj); 
	}
	
	@PostMapping
	public ResponseEntity<BibliotecarioDTO> create(@Valid @RequestBody BibliotecarioDTO dto){
		dto = new BibliotecarioDTO(bibliotecaService.create(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bibliotecario> update(@Valid @PathVariable Integer id, @RequestBody Bibliotecario obj){
		bibliotecaService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		bibliotecaService.delete(id);
			return ResponseEntity.noContent().build();
	}
	
}
