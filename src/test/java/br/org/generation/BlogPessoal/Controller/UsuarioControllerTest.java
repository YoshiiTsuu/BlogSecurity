package br.org.generation.BlogPessoal.Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.myBlog.model.Usuario;
import br.org.generation.myBlog.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario, usuarioupd;

	@BeforeAll
	public void start() {
		usuario = new Usuario("Pongo", "Ping", "44451198");
		usuarioupd = new Usuario("Pongo de Oliveira", "ping21", "44451198");
	}

	@Test
	public void deveRealizarPostUsuarios() {
	HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
	ResponseEntity<Usuario> resposta = testRestTemplate
	.exchange("/usuarios/logar", HttpMethod.POST, request, Usuario.clas
	Assert.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Disabled
	@Test
	public void deveRealizarPutUsuarios() {
	HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioupd);
	ResponseEntity<Usuario> resposta = testRestTemplate
	.exchange("/usuarios/cadastrar", HttpMethod.PUT, request, Usuario.class
	Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode())
}
