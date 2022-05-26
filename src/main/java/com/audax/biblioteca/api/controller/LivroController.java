package com.audax.biblioteca.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audax.biblioteca.domain.model.Livro;
import com.audax.biblioteca.domain.service.LivroService;
import com.audax.biblioteca.dto.LivroDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	private LivroService livroService;

	public LivroController(LivroService livroService) {
		super();
		this.livroService = livroService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> findByIdLivro(@PathVariable Integer id){
		LivroDTO objDTO = new LivroDTO(livroService.findById(id));
		return ResponseEntity.status(HttpStatus.OK).body(objDTO); 
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(){
		List<LivroDTO> list = livroService.findAll().stream()
				.map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto){
		dto = new LivroDTO(livroService.create(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping
	public ResponseEntity<LivroDTO> update(@Valid @RequestBody LivroDTO dto){
		dto = new LivroDTO(livroService.update(dto));
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
			livroService.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<LivroDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcString){
		Page<Livro> list = livroService.findPage(page, linesPerPage, orderBy, direcString);
		Page<LivroDTO> listDto = list.map(obj -> new LivroDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	  

}
