package br.org.generation.BlogPessoal.Model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.myBlog.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioModelTest {
	// invocando construtor
	public Usuario usuario;
	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/*
	 * this.nome = nome; this.usuario = usuario; this.senha = senha;
	 */
	@BeforeEach // Antes de começar inserir estes valores no teste
	public void start() {
		usuario = new Usuario("Lady Gaga", "LGaga", "*#$120,!");
	}

	@Test // Confirma se os dados passados no BeforeEach coincidem
	public void testValidationAtributos() {
		usuario.setNome("Lady Gaga");
		usuario.setUsuario("LGaga");
		usuario.setSenha("*#$120,!");
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		// Caso os dados não coincidam o erro vai aparecer por conta deste Sysout
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty()); //
	}
}
