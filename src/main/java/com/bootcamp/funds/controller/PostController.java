package com.bootcamp.funds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.funds.dto.PostDto;
import com.bootcamp.funds.service.PostService;

@RestController
@RequestMapping("/postapi/v1")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/posts")
	public ResponseEntity<PostDto> addPost(@PathVariable String username, @RequestBody PostDto dto){
		PostDto postDto = postService.createPost(username, dto);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		return new ResponseEntity<List<PostDto>>(postService.getAllPosts(), HttpStatus.OK);
	}
	
	@PutMapping("/posts/{username}/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable String username, @PathVariable Long postId, @RequestBody PostDto dto){
		return new ResponseEntity<PostDto>(postService.updatePost(username, postId, dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/posts/{username}/{postId}")
	public ResponseEntity<String> DeletePostById(@PathVariable String username, @PathVariable Long postId){
		postService.deletePost(username, postId);
		return new ResponseEntity<String>("Post with ID:: "+postId+" deleted successfully", HttpStatus.NO_CONTENT);
	}

}
