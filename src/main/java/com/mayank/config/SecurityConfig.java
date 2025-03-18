package com.mayank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mayank.security.JWTAuthenticationEntryPoint;
import com.mayank.security.JWTAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JWTAuthenticationEntryPoint point;
	@Autowired
	private JWTAuthenticationFilter filter;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").authenticated()
				.requestMatchers("/auth/login").permitAll()
				.requestMatchers("/auth/create-user").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@SuppressWarnings("deprecation")
	@Bean
	DaoAuthenticationProvider doDaoAuthenticationProvider() {
		DaoAuthenticationProvider providerbean = new DaoAuthenticationProvider();
		providerbean.setUserDetailsService(userDetailService);
		providerbean.setPasswordEncoder(passwordEncoder);
		return providerbean;
	}

}
