package com.blog.payloads;

import java.util.*;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	
	private int postId;
	private String title;
	
	private String content;
	
	//private String imgaeName="";
private String imgaeName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
	
	
	
}
