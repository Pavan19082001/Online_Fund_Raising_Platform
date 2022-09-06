package com.bootcamp.funds.controller;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.PostDto;
import com.bootcamp.funds.service.PostServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostControllerTest {
	@Autowired
	PostController postcontroller;
	
	@MockBean
	PostServiceImpl postservice;
	
	@Test
	public void getAllPostsTest(){
		List<PostDto> post= createPostsDtoMockData();
		when(postservice.getAllPosts()).thenReturn(post);
		ResponseEntity<List<PostDto>> response=postcontroller.getAllPosts();
		assert(post.size()==response.getBody().size());
		
	}
	@Test
	public void addPostTest(){
		 List<PostDto> post= createPostsDtoMockData();
		 String username="ramesh";
		when(postservice.createPost(username,post.get(0))).thenReturn(post.get(0));
		ResponseEntity<PostDto> response=postcontroller.addPost(username, post.get(0));
		assert(post.get(0).getTitle().equals(response.getBody().getTitle()));
		
	}
	@Test
	public void updatePostTest(){
		List<PostDto> post= createPostsDtoMockData();
		String username="ramesh";
		Long postid=1L;
		when(postservice.updatePost(username,postid,post.get(0))).thenReturn(post.get(0));
		ResponseEntity<PostDto> response=postcontroller.updatePost(username, postid, post.get(0));
		assert(post.get(0).getTitle().equals(response.getBody().getTitle()));
		
	}
	
	@Test
	public void deletePostTest(){
		List<PostDto> post= createPostsDtoMockData();
		String username="ramesh";
		Long postid=1L;
		String responsestr="Post with ID:: "+post.get(0).getId()+" deleted successfully";
		when(postservice.deletePost(username,postid)).thenReturn(responsestr);
		ResponseEntity<String> response=postcontroller.DeletePostById(username, postid);
		assertNotSame(responsestr, response.getBody());
		
	}
	private List<PostDto> createPostsDtoMockData(){
		List<PostDto> posts = new ArrayList<>();
		
		PostDto post = new PostDto();
		post.setTitle("donation");
		post.setId(1l);
		post.setDescription(" funds donation");
		
		
		posts.add(post);
		
		return posts;		
	}

	

	

}
