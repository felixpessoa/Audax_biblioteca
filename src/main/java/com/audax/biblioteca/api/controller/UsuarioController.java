package com.audax.biblioteca.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.audax.biblioteca.api.controller.exception.BusinessException;
import com.audax.biblioteca.domain.model.TokenReponse;
import com.audax.biblioteca.domain.model.Usuario;
import com.audax.biblioteca.domain.service.exception.UsuarioService;
import com.audax.biblioteca.domain.service.jwt.JwtService;

@CrossOrigin(origins = "http://localhost", maxAge = 4200)
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;

	public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada)	;
		return usuarioService.createUsuario(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario update(@PathVariable Integer usuarioId, @RequestBody Usuario usuario ) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada)	;
		return usuarioService.upDateUsuario(usuarioId, usuario);
	}
	
	@CrossOrigin
	@PostMapping("/auth")
	public TokenReponse autenticar(@RequestBody Usuario usuario) {
		try {	
			Usuario obj = Usuario.builder()	
							.login(usuario.getLogin())
							.password(usuario.getPassword()).build();
			UserDetails usuarioAutenticado = usuarioService.autenticar(obj);
			String token = jwtService.gerarToken(obj);				
			return new TokenReponse(obj.getLogin(), token);
		} catch (UsernameNotFoundException | BusinessException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
