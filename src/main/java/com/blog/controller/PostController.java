package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;

import com.blog.config.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;

import com.blog.payloads.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
@RestController
@RequestMapping("/api/")
@SecurityRequirement(name="scheme1")
public class PostController {
	@Autowired
	private PostServices postServices;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable int userId,@PathVariable int categoryId
			){
		PostDto createPost=this.postServices.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//get by user By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId){
		List<PostDto> posts=this.postServices.getPostByUser(userId);
		System.out.println(posts);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
		List<PostDto> posts=this.postServices.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	// get all post
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDto>> getAllPost(
//	    @RequestParam(value="pageNumber", defaultValue="0", required=false) int pageNumber,
//	    @RequestParam(value="pageSize", defaultValue="5", required=false) int pageSize) {
//
//	    List<PostDto> allPost = this.postServices.getAll(pageNumber, pageSize);
//	    return new ResponseEntity<>(allPost, HttpStatus.OK);
//	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
	    @RequestParam(value="pageNumber", defaultValue=AppContest.Page_NUMBER, required=false) int pageNumber,
	    @RequestParam(value="pageSize", defaultValue=AppContest.PAGE_SIZE, required=false) int pageSize
	    ,@RequestParam(value="sortBy",defaultValue=AppContest.SORT_BY,required=false) String sortBy,
	    @RequestParam(value="sortDir",defaultValue=AppContest.SORT_DIR,required=false) String sortDir) {
		
	    PostResponse postResponse = this.postServices.getAll(pageNumber, pageSize,sortBy,sortDir);
	    return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	
	//get single post
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		PostDto post=this.postServices.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	// delete post
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable int postId) {
		this.postServices.deletePost(postId);
		return new ApiResponse("Post is successfully is deleted !!",true);
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId){
		
		PostDto updatePost=this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//Search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable String keywords
			){
		List<PostDto>result=this.postServices.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//Post imgae upload
	
	@PostMapping("posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile file,@PathVariable int postId) throws IOException {
		PostDto postDto=this.postServices.getPostById(postId);

		String fileName=this.fileService.uploadImage(path, file);
		postDto.setImgaeName(fileName);
		PostDto updatePost=this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/posts/image/{imageName}",produces=MediaType.IMAGE_PNG_VALUE)
	public void downloadImage(
			@PathVariable String imageName,HttpServletResponse response)throws IOException{
		
		System.out.println("we are here");
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
}
