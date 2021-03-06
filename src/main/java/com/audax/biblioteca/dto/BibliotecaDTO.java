package com.audax.biblioteca.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.audax.biblioteca.domain.model.Biblioteca;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BibliotecaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCadastro;

	public BibliotecaDTO() {
		super();
	}

	public BibliotecaDTO(Biblioteca obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.dataCadastro = obj.getDataCadastro();
	}

	
}
