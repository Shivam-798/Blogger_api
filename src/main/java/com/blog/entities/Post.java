package com.blog.entities;

import java.util.*;

import com.blog.payloads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	
	private String title;
	
	@Column(length=10000)
	private String content;
	
	private String imgaeName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
}
