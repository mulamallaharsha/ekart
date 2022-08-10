package com.codebag.ekart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebag.ekart.entity.User;
import com.codebag.ekart.models.AuthResponse;
import com.codebag.ekart.repository.UserRepository;
import com.codebag.ekart.utils.JwtTokenUtil;



@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtils;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/register")
	public User register(@RequestBody User user) {

		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user = userRepository.save(user);
		return user;

	}

	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {

		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

			User userDetails = (User) authentication.getPrincipal();
			String accessToken = jwtTokenUtils.generateAccessToken(userDetails);

			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
