package com.audax.biblioteca.dto;

import com.audax.biblioteca.domain.model.Livro;

import lombok.Data;

@Data
public class LivroDTO {
	
	private Integer id;
	private String nome;
	
	public LivroDTO() {
		super();
	}
	
	public LivroDTO(Livro obj ) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
	}


}
