package ma.emsi.foot.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.beans.factory.annotation.Autowired;

import ma.emsi.foot.service.UtilisateurService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UtilisateurService utilisateurService ;
	@Autowired
	PasswordEncoder passwordEncoder ;
	@Autowired
	JWTAuthorizationFilter authorizationFilter ;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UtilisateurService utilisateurService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(utilisateurService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		  http.cors();
		  http.httpBasic().disable();
		  http	 
				   
	                .csrf(csrf -> csrf.disable())
	                  
	                .authorizeHttpRequests((auth) -> { auth
	                	.requestMatchers("/authenticate").permitAll()
	                    .requestMatchers("/creneaux").hasRole("ADMIN")
	                    .requestMatchers("/articles").hasRole("ADMIN")
	                    .anyRequest().authenticated();
	                    
	                });
	               
	                               
		  http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class); 
		  return http.build();
	    }
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(utilisateurService).passwordEncoder(passwordEncoder);
		
	}
	
	}

