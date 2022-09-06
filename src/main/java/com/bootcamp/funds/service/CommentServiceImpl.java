package com.bootcamp.funds.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bootcamp.funds.dto.CommentDto;
import com.bootcamp.funds.exceptions.APIException;
import com.bootcamp.funds.exceptions.CommentNotFoundException;
import com.bootcamp.funds.exceptions.PostNotFoundException;
import com.bootcamp.funds.model.Comment;
import com.bootcamp.funds.model.Post;
import com.bootcamp.funds.repository.CommentRepository;
import com.bootcamp.funds.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	PostRepository postRepo;

	@Override
	public CommentDto createComment(Long postId, CommentDto dto) {
		Comment comment = mapper.map(dto, Comment.class);

		// retrieve post entity by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		// set post to comment entity
		comment.setPost(post);

		Comment newComments = commentRepo.save(comment);
		return mapper.map(newComments, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto dto) {
		// retrieve comments by commentId
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new CommentNotFoundException());

		// retrieve post entity by id
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		if (!(comment.getPost().getId() == post.getId())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "comments doesnot belongs to the post");
		}

		comment.setText(dto.getText());

		Comment newComments = commentRepo.save(comment);
		return mapper.map(newComments, CommentDto.class);
	}

	@Override
	public String deleteCommentById(Long postId, Long commentId) {
		// retrieve comments by commentId
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new CommentNotFoundException());

		// retrieve post entity by id
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		if (!(comment.getPost().getId() == post.getId())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "comments doesnot belongs to the post");
		}

		commentRepo.deleteById(commentId);
		return "Comment with ID:: " + commentId + " is deleted successfully";
	}

}
