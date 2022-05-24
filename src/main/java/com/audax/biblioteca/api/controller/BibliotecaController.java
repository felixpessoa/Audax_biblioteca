package com.audax.biblioteca.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.audax.biblioteca.domain.service.BibliotecaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {
	
	private BibliotecaService bibliotecaService;

	public BibliotecaController(BibliotecaService bibliotecaService) {
		super();
		this.bibliotecaService = bibliotecaService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Biblioteca> findByIdBiblioteca(@PathVariable Integer id){
		Biblioteca obj = bibliotecaService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj); 
	}

}
