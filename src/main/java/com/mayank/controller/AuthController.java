package com.mayank.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.entity.User;
import com.mayank.model.JWTRequest;
import com.mayank.model.JWTResponse;
import com.mayank.security.JWTHelper;
import com.mayank.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTHelper helper;
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal)
	{
		return principal.getName();
	}
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request)
	{
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);
		
		JWTResponse response = JWTResponse.builder()
				.token(token)
				.username(userDetails.getUsername()).build();
		return new ResponseEntity<JWTResponse>(response, HttpStatus.OK);
		
	}

	private void doAuthenticate(String email, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e)
		{
			throw new BadCredentialsException("Invalid Username or Password!");
		}
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler()
	{
		return "Invalid Credentials!!";
	}
	

}
