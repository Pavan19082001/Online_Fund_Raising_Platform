package com.bootcamp.funds.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bootcamp.funds.dto.PostDto;
import com.bootcamp.funds.exceptions.APIException;
import com.bootcamp.funds.exceptions.PostNotFoundException;
import com.bootcamp.funds.exceptions.UserNotFoundException;
import com.bootcamp.funds.model.Post;
import com.bootcamp.funds.model.User;
import com.bootcamp.funds.repository.PostRepository;
import com.bootcamp.funds.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PostRepository postRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public PostDto createPost(String username, PostDto dto) {
		Post post = mapper.map(dto, Post.class);

		// retrieve user entity by username
		User user = userRepo.findUserByUserName(username).orElseThrow(() -> new UserNotFoundException());

		// set user to post entity
		post.setUser(user);

		Post newPost = postRepo.save(post);
		return mapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(String username, Long postId, PostDto dto) {
		// retrieve user entity by username
		User user = userRepo.findUserByUserName(username).orElseThrow(() -> new UserNotFoundException());

		// retrieve post entity by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		if (!(post.getUser().getId() == user.getId())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "post doesnot belongs to the user");
		}

		post.setDescription(dto.getDescription());

		Post updatedPost = postRepo.save(post);
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public String deletePost(String username, Long postId) {
		// retrieve user entity by username
		User user = userRepo.findUserByUserName(username).orElseThrow(() -> new UserNotFoundException());

		// retrieve post entity by id
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		if (!(post.getUser().getId() == user.getId())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "post doesnot belongs to the user");
		}

		postRepo.delete(post);
		return "Post with ID:: " + postId + " deleted successfully";
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> postData = postRepo.findAll();

		return postData.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
