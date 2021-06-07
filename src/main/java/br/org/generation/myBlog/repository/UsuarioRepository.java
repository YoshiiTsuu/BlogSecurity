package br.org.generation.myBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.myBlog.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
	
	public Optional<Usuario> findByUsuario(String usuario); // Aqui tem que ser optional, e o findBy é referente ao usuario que esta na classe model
	//Optional pois os valores podem vir nulos!
	

}
