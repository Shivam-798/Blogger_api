package com.blog.services;
import java.util.*;
import com.blog.payloads.*;
import com.blog.entities.*;
public interface PostServices {

	PostDto createPost(PostDto postDto,int userID,int categoryId);
	
	PostDto updatePost(PostDto postDto,int postId);
	
	void deletePost(int id);
	
	PostResponse getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(int postId);
	
	List<PostDto> getPostsByCategory(int categoryId);
	
	List<PostDto> getPostByUser(int userId);
	
	List<PostDto> searchPosts(String keyword);
}
