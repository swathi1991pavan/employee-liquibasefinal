package com.example.employee.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.jpa.dto.AuthRequest;
import com.example.employee.jpa.service.JwtService;

@RestController
@RequestMapping("/api/authenticate")
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	
	
	 
	@PostMapping("/v1")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	}

}





















/*{
"username" : "Swathi",
"password" : "swathi"
}*/