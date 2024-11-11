package com.example.groceryShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.groceryShopping.dto.SignUpRequest;
import com.example.groceryShopping.entity.Role;
import com.example.groceryShopping.entity.User;
import com.example.groceryShopping.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	
	public User signUp(SignUpRequest request) {
		
		User user = new User();
		
		user.setUsername(request.getUsername());
		user.setFirstname(request.getFirstname());
		user.setLastnae(request.getLastname());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		return userRepo.save(user);		
	}

	public UserDetails authenticate(String username, String password) {
		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	     return userRepo.findByUsername(username); 
	}
	
}
