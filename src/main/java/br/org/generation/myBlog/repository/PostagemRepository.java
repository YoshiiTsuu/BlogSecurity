package br.org.generation.myBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.generation.myBlog.model.Postagem;
//É importante que o repository seja uma interface que se estende da Classe post!

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	// IgnoreCase ele ignora maiusculas e minusculas e faz a busca em todo o db
	// findAll By Tituloé o objeto da classe Post

	public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);
	// Para que encontre pelo texto

}
