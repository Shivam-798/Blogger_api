package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.repositories.*;
import com.blog.entities.User;
import com.blog.exceptions.*;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//loading user from database f
	User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResouceNotFoundException("User", "email "+username, 0));
		
		
		return user;
	}

}
