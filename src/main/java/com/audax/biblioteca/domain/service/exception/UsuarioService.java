package com.audax.biblioteca.domain.service.exception;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.audax.biblioteca.domain.model.Usuario;
import com.audax.biblioteca.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	private PasswordEncoder encoder;
	private UsuarioRepository usuarioRepository;
	
	
	public UsuarioService(PasswordEncoder encoder, UsuarioRepository usuarioRepository) {
		super();
		this.encoder = encoder;
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		String[] roles = usuario.getAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

		return User.builder().username(usuario.getLogin()).password(usuario.getPassword()).roles(roles).build();
	}
	
	public Usuario createUsuario(Usuario usuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findByLogin(usuario.getLogin());
		if (optUsuario.isPresent()) {
			if (!optUsuario.get().getUsuarioId().equals(usuario.getUsuarioId())) {
				throw new ObjectNotFoundException("Login já cadastrado!");
			}
		}
		
		if (usuario.getUsuarioId() == null) {
			usuario.setAdmin(false);
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario upDateUsuario(Integer usuarioId, Usuario usuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);
			if(optUsuario.isEmpty()) {
				throw new ObjectNotFoundException("Usuario não Cadastrado.");
			}
		usuario.setUsuarioId(usuarioId);
		return createUsuario(usuario);
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhaTrue = encoder.matches(usuario.getPassword(), user.getPassword());
		if (senhaTrue) {
			return user;
		}
		throw new ObjectNotFoundException("Login ou Senha inválida!");
	}
	

}
