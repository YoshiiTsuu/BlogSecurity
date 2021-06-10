package br.org.generation.myBlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Esta é uma classe de configuração de segurança
@EnableWebSecurity // Enable vem do import que foi colocado no pom.xml
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService service;
	// Injetado de uma classe já existente na websecurity

	@Override // É um método sobreescrito
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {// throws é uma trativa de erros
		auth.userDetailsService(service);
	}

	@Bean //Possibilita que utilize em outras classes
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override//Básico para fazer login
	//"como se fosse controller só que de segurança"
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/usuarios/logar").permitAll()// Permite que alguns endpoints sejam
																			// acessados sem que seja necessário login e
																			// senha
				.antMatchers("/usuarios/cadastrar").permitAll().anyRequest().authenticated() // todas outras requisições
																								// deverão ser
																								// autenticadas, define
																								// que no header deverá
																								// ser passado a chave
																								// (token)
				.and().httpBasic()// padrão basic para gerar chave token
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Indica qual tipo de
																									// sessão será
																									// utilizada
				// Stateless pois não guarda sessão/estado
				.and().cors()//aceite requisições de qualuqer origem
				.and().csrf().disable();// evita ataques a sua aplicação
					//desabilita pois estamos em ambiente de trabalho
	}
}
