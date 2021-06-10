package br.org.generation.BlogPessoal.Repository;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.myBlog.model.Usuario;
import br.org.generation.myBlog.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTeste {
	@Autowired
	private UsuarioRepository repository;
	public Usuario usuario;

	@BeforeAll //Antes de fazer teste inserir os valores na tabela
	public void start() {
		usuario = new Usuario("Lady Gaga", "LGaga", "*#$120,!");
		if (repository.findFirstByNome(usuario.getNome() == null)) //Aqui ele faz uma verificação
		//antes de inserir o valor na tabela, caso o valor não exista ele salva na tabela
			repository.save(usuario);
		usuario = new Usuario("Lady Maddona", "Beatles", "*#$120,!");
		if (repository.findFirstByNome(usuario.getNome() == null))
			repository.save(usuario);

		usuario = new Usuario("Radio Gaga", "Queen", "*#$120,!");
		if (repository.findFirstByNome(usuario.getNome() == null))
			repository.save(usuario);

	}
	
	@Test
	public void findByNomeRetornaUsuario() throws Exception {
	Usuario usuario = repository.findFirstByNome("Lady");
	Assert.assertTrue(usuario.getNome().equals("Lady"));
	}
}
