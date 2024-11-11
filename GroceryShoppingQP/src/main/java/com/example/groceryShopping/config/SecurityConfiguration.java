package com.example.groceryShopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.groceryShopping.entity.Role;
import com.example.groceryShopping.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private JwtAuthenticationFilter jwtFilter ;
	
	@Autowired
	private UserService userService;
	
	public SecurityConfiguration(UserService userService,JwtAuthenticationFilter filter) {
	    this.userService = userService;
	    this.jwtFilter = filter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(request -> request.requestMatchers("/api/auth/login","/api/auth/signup").permitAll()
				.requestMatchers("/api/admin/").hasAnyAuthority(Role.ADMIN.name())
				.requestMatchers("/api/user/").hasAnyAuthority(Role.USER.name())
				.anyRequest().authenticated())
		
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticaationProvider = new DaoAuthenticationProvider();
		authenticaationProvider.setUserDetailsService(userService);
		authenticaationProvider.setPasswordEncoder(passwordEncoder());
		return authenticaationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
			throws Exception {
		return config.getAuthenticationManager();
	}

}
