package br.org.generation.myBlog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.myBlog.model.Usuario;

public class UserDetailsImp implements UserDetails {
	//pede para adicionar todos métodos de implementação
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	
	//Construtor de classe para que use o getUsuario e senha
	public UserDetailsImp (Usuario user) {
		this.username = user.getUsuario();
		this.password = user.getSenha();
	}
	//Construtor vazio
	public UserDetailsImp() {}
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //credencial geralmente é a senha
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
