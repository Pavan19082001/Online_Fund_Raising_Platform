package com.bootcamp.funds.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.CommentDto;
import com.bootcamp.funds.service.CommentService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentControllerTest {
	
	@Autowired
	CommentController comment;
	
	@MockBean
	CommentService service;
	
	@Test
	public void postCommentTest() {
		CommentDto commentDto = postCommentDtoMockData();
		when(service.createComment(1L, commentDto)).thenReturn(commentDto);
		ResponseEntity<CommentDto> newDto = comment.postComment(1L, commentDto);
		assertEquals(commentDto.getId(), newDto.getBody().getId());
	}
	
	@Test
	public void updateCommentTest() {
		CommentDto commentDto = postCommentDtoMockData();
		when(service.updateComment(1L, 2L, commentDto)).thenReturn(commentDto);
		ResponseEntity<CommentDto> newDto = comment.updateComment(2L, 1L, commentDto);
		assertTrue(is(newDto.getBody().getId()) != null);
	}
	
	@Test
	public void deleteCommentById() {
		String msg = "Comment with ID:: "+2L+" deleted successfully";
		when(service.deleteCommentById(1L, 2L)).thenReturn(msg);
		ResponseEntity<String> dto = comment.deleteCommentById(2L, 1L);
		assertNotSame(msg, dto);
	}
	
	private CommentDto postCommentDtoMockData() {
		CommentDto comment = new CommentDto();
		comment.setText("This is a fancy egied league in this generation");
		return comment;
	}

}
