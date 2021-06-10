package br.org.generation.myBlog.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.org.generation.myBlog.model.Login;
import br.org.generation.myBlog.model.Usuario;
import br.org.generation.myBlog.repository.UsuarioRepository;
//Regras de negócio, caso só autorize maiores de 18
// E o usuario não pode ser duplicado
// nem email
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	// Garante que a senha cadastrada pelo usuario seja codificada, e impede que
	// quem tenha acesso ao banco de dados possa ver a senha do usuario pois estará criptografada
	
	public Usuario CadastrarUsuario(Usuario usuario) { //Cadastramento de usuario
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}
	public Optional<Login> Logar(Optional<Login> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//Criptografa a senha
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		//Cria um objeto usuario    //E recebe o usuario que esta gravada em seu banco de dados
		if (usuario.isPresent()) {//procura o usuario e faz um if
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { 
				//compara a senha salva com banco de dados, e a senha do login
				//encoder.matches serve para ver se as senhas são equivalentes
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				//Codifica em base 64
				String authHeader = "Basic " + new String(encodedAuth);
				//Estas três linhas servem para fazer o token 31, 32, 34.
				user.get().setToken(authHeader); //insere o token dentro do login
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());

				return user;
				//retorna user com todas informações "token, nome de usuario e senha"
			}
		}
		return null;
	}

}