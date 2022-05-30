package com.audax.biblioteca.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;
	@NotEmpty(message = "Login é Obrigatoria!")
	private String login;
	@NotEmpty(message = "Senha é Obrigatoria!")
	private String password;
	private Boolean admin;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer usuarioId, @NotEmpty(message = "Login é Obrigatoria!") String login,
			@NotEmpty(message = "Senha é Obrigatoria!") String password, Boolean admin) {
		super();
		this.usuarioId = usuarioId;
		this.login = login;
		this.password = password;
		this.admin = admin;
	}

}
