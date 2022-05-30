package com.audax.biblioteca.domain.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TokenReponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String login;
	private String token;
	
	public TokenReponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TokenReponse(String login, String token) {
		super();
		this.login = login;
		this.token = token;
	}



	
	
	

}
