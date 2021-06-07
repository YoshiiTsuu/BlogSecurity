package br.org.generation.myBlog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.myBlog.model.Login;
import br.org.generation.myBlog.model.Usuario;
import br.org.generation.myBlog.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

		
		@Autowired
		private UsuarioService usuarioService;

		@PostMapping("/logar")
		public ResponseEntity<Login> Authentication(@RequestBody Optional<Login> user) {
			return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());// or else caso senha ou login estiver incorreto ele responde com unauthorized
		}

		@PostMapping("/cadastrar")
		public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(usuarioService.CadastrarUsuario(usuario)); //Cadastrarusuario da classe service
		}
	}
