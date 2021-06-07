package br.org.generation.myBlog.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.myBlog.model.Usuario;
import br.org.generation.myBlog.repository.UsuarioRepository;

//Esta classe é um serviço
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	public UsuarioRepository repository;
	
	@Override // outro método des sobreescreve
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByUsuario(username);
		//procura o usuario que foi inserido, serve para verificar se o usuario existe
		user.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));// função lambda para caso ocorra algum erro
		return user.map(UserDetailsImp::new).get();
	}
}
