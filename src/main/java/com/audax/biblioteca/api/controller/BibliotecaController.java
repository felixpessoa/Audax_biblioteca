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

import com.audax.biblioteca.domain.service.BibliotecaService;
import com.audax.biblioteca.dto.BibliotecaDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {
	
	private BibliotecaService bibliotecaService;

	public BibliotecaController(BibliotecaService bibliotecaService) {
		super();
		this.bibliotecaService = bibliotecaService;
	}
	
	@GetMapping
	public ResponseEntity<List<BibliotecaDTO>> findAll(){
		List<BibliotecaDTO> list = bibliotecaService.findAll().stream()
				.map(obj -> new BibliotecaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BibliotecaDTO> findByIdBiblioteca(@PathVariable Integer id){
		BibliotecaDTO objDTO = new BibliotecaDTO(bibliotecaService.findById(id));
		return ResponseEntity.status(HttpStatus.OK).body(objDTO); 
	}
	
	@PostMapping
	public ResponseEntity<BibliotecaDTO> create(@Valid @RequestBody BibliotecaDTO dto){
		dto = new BibliotecaDTO(bibliotecaService.create(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BibliotecaDTO> update(@Valid @PathVariable Integer id, @RequestBody BibliotecaDTO dto){
		dto = new BibliotecaDTO(bibliotecaService.update(id, dto));
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		bibliotecaService.delete(id);
			return ResponseEntity.noContent().build();
	}
	
}
