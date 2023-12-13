package org.java.spring.auth.db.conf;

import org.java.spring.auth.db.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		.requestMatchers("/pizze/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/edit/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/delete/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/ingredienti/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/ingredienti/delete/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("pizza/{id}/discount/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/{id}/discount/{discount_id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/**").hasAnyAuthority("USER", "ADMIN")
		.and().formLogin()
		.and().logout()
		;
		
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserService();
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
}