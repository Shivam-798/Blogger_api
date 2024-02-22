package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entities.*;
import java.util.*;
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
