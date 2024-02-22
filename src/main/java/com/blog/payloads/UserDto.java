package com.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=5,message="sahi se bhar be")
	private String name;
	
	@Email(message="Sahi email dal be")
	private String email;
	
	@NotEmpty(message="kuch  bhar bhai")
	@Size(min=4,max=12)
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles=new HashSet<>();
}
//iska use api mein expose karne mein hoga 
//ham nahi chahete hai koi bhi enitities ko access kare direct uski jagha vo dto ko access kare
