package com.bootcamp.funds.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.PostDto;
import com.bootcamp.funds.model.Post;
import com.bootcamp.funds.repository.PostRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceImplTest {

	@Autowired
	PostServiceImpl postservice;
	
	@Autowired
	ModelMapper modelMapper;
	
	@MockBean
	PostRepository postrepo;
	
	@Test
	public void getAllPostsTest() {
		List<Post> post = createsPostMockData();
		when(postrepo.findAll()).thenReturn(post);
		List<PostDto> postDto = postservice.getAllPosts();
		assertEquals(post.size(), postDto.size());
	}
	
	private List<Post> createsPostMockData(){
		List<Post> posts = new ArrayList<>();
		
		Post post = new Post();
		
		post.setTitle("donation");
		post.setId(1L);
		post.setDescription(" funds donation");
		
		posts.add(post);
		
		return posts;		
	}
	

	
	
}
