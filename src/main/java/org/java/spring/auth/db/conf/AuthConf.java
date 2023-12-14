package org.java.spring.auth.db.conf;

import org.java.spring.auth.db.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@CrossOrigin
public class AuthConf {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.cors().disable()
		.authorizeHttpRequests()
		.requestMatchers("/pizze/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/edit/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/delete/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/ingredienti/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/ingredienti/delete/{id}").hasAnyAuthority("ADMIN")
		.requestMatchers("pizza/{id}/discount/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/pizza/{id}/discount/{discount_id}").hasAnyAuthority("ADMIN")
		.requestMatchers("/api/v1.0/**").permitAll()
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
//	@Bean
//    FilterRegistrationBean<CorsFilter> getCorsSettings() {
//		
//        final CorsConfiguration config = new CorsConfiguration();
//        
//        // OPTIONS
////        config.setAllowCredentials(true);
//        
//        config.addAllowedOrigin("http://localhost:5173"); // DEVELOP FE SERVER
//        
//        // HEADERS
//        config.addAllowedHeader("Content-Type");
//        config.addAllowedHeader("Authorization");
//        config.addAllowedHeader("X-XSRF-TOKEN");
//        config.addAllowedHeader("Accept");
//        
//        // METHODS
//        config.addAllowedMethod(HttpMethod.GET);
//        config.addAllowedMethod(HttpMethod.POST);
//		config.addAllowedMethod(HttpMethod.PUT);
//        config.addAllowedMethod(HttpMethod.DELETE);
//        
//        // SET CONFIG ON PATHS
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        
//        final FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        
//        return bean;
//    }
}
