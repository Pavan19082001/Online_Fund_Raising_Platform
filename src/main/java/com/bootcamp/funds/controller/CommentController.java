package com.bootcamp.funds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.funds.dto.CommentDto;
import com.bootcamp.funds.service.CommentService;

@RestController
@RequestMapping("/commentapi/v1")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comments/{postId}")
	public ResponseEntity<CommentDto> postComment(@PathVariable Long postId, @RequestBody CommentDto dto){
		CommentDto commentDto = commentService.createComment(postId, dto);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/comments/{commentId}/{postId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @PathVariable Long postId, @RequestBody CommentDto dto){
		return new ResponseEntity<CommentDto>(commentService.updateComment(postId, commentId, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}/{postId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable Long commentId, @PathVariable Long postId){
		commentService.deleteCommentById(postId, commentId);
		return new ResponseEntity<String>("Comment with ID:: "+commentId+" deleted successfully", HttpStatus.NO_CONTENT);
	}

}
