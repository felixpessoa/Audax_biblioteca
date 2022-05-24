package com.audax.biblioteca.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Bibliotecario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCriacao;

	@JsonBackReference
	@ManyToMany
	@JoinTable( name = "BIBLIOTECARIO_BIBLIOTECA",
			joinColumns = @JoinColumn(name = "bibliotecario_id"),
			inverseJoinColumns = @JoinColumn(name = "biblioteca_id")
	)
	private List<Biblioteca> bibliotecas = new ArrayList<>();

	public Bibliotecario() {
		super();
	}

	public Bibliotecario(Integer id, String nome, LocalDateTime dataCriacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
	}

}
