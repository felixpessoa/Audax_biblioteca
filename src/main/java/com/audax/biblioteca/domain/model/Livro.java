package com.audax.biblioteca.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Livro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Digite o titulo do livro")
	@NotNull(message = "Digite o titulo do livro")
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable( name = "LIVRO_BIBLIOTECA",
			joinColumns = @JoinColumn(name = "livro_id"),
			inverseJoinColumns = @JoinColumn(name = "biblioteca_id")
	)
	private List<Biblioteca> bibliotecas = new ArrayList<>();
	
	public Livro() {
		super();
	}
	
	public Livro(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}



	
	
	

}
