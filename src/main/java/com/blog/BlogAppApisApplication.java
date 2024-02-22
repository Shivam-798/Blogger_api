package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppContest;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepo;

@SpringBootApplication  //ye wali annotation hame config wali suidth provide karti hai isliye isme ham bean provide kar sakte hai modelmapper ki
public class BlogAppApisApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role1=new Role();
			role1.setId(AppContest.ADMIN_ROLE);
			role1.setName("ADMIN_USER");
			
			Role role2=new Role();
			role2.setId(AppContest.NORMAL_USER);
			role2.setName("NORMAL_USER");
			
			List<Role> roles=List.of(role1,role2);
			this.roleRepo.saveAll(roles);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
