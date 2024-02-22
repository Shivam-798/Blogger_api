package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.ApiException;
import com.blog.payloads.JwtAuthRequest;
import com.blog.payloads.JwtAuthResponse;
import com.blog.payloads.UserDto;
import com.blog.security.JwtTokenHelper;
import com.blog.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirement(name="scheme1")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
		@RequestBody JwtAuthRequest request	) throws Exception
	
	{
		this.authenticate(request.getUsername(),request.getPassword());
		System.out.println("Response mein kya aya hai   ");
		System.out.println("Response mein kya aya hai   " +request);

		UserDetails details=this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(details);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		System.out.println("Response mein kya aya hai   " +response.getToken());
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	private void authenticate (String username,String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password); 
		try {
			this.authenticationManager.authenticate(authenticationToken);

		}
		catch(BadCredentialsException e) {
			System.out.println("Invalid Details ");
			throw new ApiException("Invalid username or Password");
		}
		
	}

	//Register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto>resgisterUser(@RequestBody UserDto userDto){
		
	UserDto resgisterUser=this.userService.resgisterNewUser(userDto);
	return new ResponseEntity<UserDto>(resgisterUser,HttpStatus.ACCEPTED);
	}
}
