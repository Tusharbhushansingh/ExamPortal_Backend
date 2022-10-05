package com.exam.config.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.model.JwtUtils;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.impl.UserdetailsServiceImpl;


@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired 
	private UserdetailsServiceImpl userdetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
//	@Autowired
//	private CorsConfigurationSource corsConfigurationSource;
		
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			
			this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found !!");
		}
		
		//authenticate
		UserDetails userDetails = this.userdetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	private void authenticate(String username,  String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch(DisabledException e) {
			throw new Exception("USER DISABLED " +e.getMessage());
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
	}
	
	//return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		  return ((User) this.userdetailsServiceImpl.loadUserByUsername(principal.getName()));
	}
	
}
