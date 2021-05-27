package br.org.generation.myBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.myBlog.model.Postagem;
import br.org.generation.myBlog.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping() //Findall
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}") //GetById
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))// devolve um objeto resposta
				.orElse(ResponseEntity.notFound().build());// caso contrário devolve um notfound
	}

	@GetMapping("/titulo/{titulo}") //GetByTitulo
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo)); // Este método retorna uma
																							// lista
	}

	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Postagem>> GetByTexto(@PathVariable String texto) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem update){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(update));		
	}
	@DeleteMapping
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}