package com.blog.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostServices;
import com.blog.repositories.*;
import com.blog.entities.*;
import com.blog.exceptions.*;

@Service
public class PostImpl implements PostServices {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	 @Autowired
	private CategoryRepo categoryRepo;
	 
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("User", "User Id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category","categoryId",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);//post mein sirf do hi chize hai title and content baki ko ham se karege
		post.setImgaeName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("Post","postId",postId));
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImgaeName(postDto.getImgaeName());
	Post updatePost=this.postRepo.save(post);
	return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(int id) {
		Post post1=this.postRepo.findById(id).orElseThrow(()->new ResouceNotFoundException("Post","postId",id));
		this.postRepo.delete(post1);

	}

//	@Override
//	public List<PostDto> getAll(int pageNumber,int pageSize) {
//		Pageable p=PageRequest.of(pageNumber, pageSize);
//		Page <Post> pagePost=this.postRepo.findAll(p);
//		
//		System.out.println("Page number: " + pagePost.getNumber());
//		System.out.println("Total pages: " + pagePost.getTotalPages());
//		System.out.println("Total elements: " + pagePost.getTotalElements());
//		
//		List<Post> allPosts=pagePost.getContent();
//		
//		System.out.println();
//	
//		List<PostDto>list=	allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//			return list; 	
//		
//	}
	
	@Override
	public PostResponse getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page <Post> pagePost=this.postRepo.findAll(p);
		
		System.out.println("Page number: " + pagePost.getNumber());
		System.out.println("Total pages: " + pagePost.getTotalPages());
		System.out.println("Total elements: " + pagePost.getTotalElements());
		
		List<Post> allPosts=pagePost.getContent();
		
		System.out.println(allPosts);
	
		List<PostDto>list=	allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(list);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
			return postResponse; 	
		
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post1=this.postRepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("Post","postId",postId));

		return this.modelMapper.map(post1, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category", "categoryId", categoryId));
		
		List<Post> posts=this.postRepo.findByCategory(cat);
	List<PostDto>postDto=	posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User users=this.userRepo.findById(userId).orElseThrow(()->new ResouceNotFoundException("User", "userId", userId));
		List<Post>posts=this.postRepo.findByUser(users);
		List<PostDto>postDto=	posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		System.out.println(postDto);

		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post>posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto>postDto=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}
