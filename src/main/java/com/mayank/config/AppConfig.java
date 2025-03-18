package com.mayank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
	// When using In Memory User Details other then Database
//	    @Bean
//	    public UserDetailsService userDetailsService() {
//	        UserDetails user1 = User.builder().username("mayank").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
//	        UserDetails user2 = User.builder().username("pradeep").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
//	        return new InMemoryUserDetailsManager(user1, user2);
//	    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
