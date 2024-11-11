package com.example.groceryShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.groceryShopping.dto.JwtAuthenticationResponse;
import com.example.groceryShopping.dto.LoginRequest;
import com.example.groceryShopping.dto.SignUpRequest;
import com.example.groceryShopping.entity.User;
import com.example.groceryShopping.service.AuthService;
import com.example.groceryShopping.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authService.signUp(request));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest authRequest) {
		// Authenticate the user
		UserDetails userDetails = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());

		// Generate JWT token
		String accessToken = jwtService.generateAccessToken(userDetails);
		String refreshToken = jwtService.generateRefreshToken(userDetails);
		// Return token in response
		return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken));
	}

}
