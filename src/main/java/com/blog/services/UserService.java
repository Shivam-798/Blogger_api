package com.blog.services;

import java.util.List;

import com.blog.entities.User;
import com.blog.payloads.UserDto;

public interface UserService {
	UserDto resgisterNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
