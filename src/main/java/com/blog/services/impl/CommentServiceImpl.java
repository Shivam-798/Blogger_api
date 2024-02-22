package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.*;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResouceNotFoundException("Post", "postId", postId));
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComm=this.commentRepo.save(comment);
		System.out.println(savedComm.getContent());
		return this.modelMapper.map(savedComm, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {

		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResouceNotFoundException("Comment", "commentId", commentId));;
		this.commentRepo.delete(com);
	}

}
