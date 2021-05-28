package br.org.generation.myBlog.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.myBlog.model.Tema;


public interface TemaRepository extends JpaRepository<Tema, Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase( String descricao);
}