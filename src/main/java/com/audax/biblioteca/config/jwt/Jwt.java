package com.audax.biblioteca.config.jwt;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class Jwt {

	private String expiracao = "10";
	private String chaveAssinatura = "YmlibGlvdGVjYSBhdWRheA==";
	
}
