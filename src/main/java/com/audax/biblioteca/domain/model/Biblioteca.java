package com.audax.biblioteca.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Biblioteca implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCadastro;
	
	@ManyToMany(mappedBy = "bibliotecas")
	private List<Livro> livros = new ArrayList<>();
	
	@ManyToMany(mappedBy = "bibliotecas")
	private List<Bibliotecario> bibliotecarios = new ArrayList<>();

	public Biblioteca() {
		super();
	}
	
	public Biblioteca(Integer id, String nome, LocalDateTime dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
	}
	

}
