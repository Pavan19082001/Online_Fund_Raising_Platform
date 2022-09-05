package com.bootcamp.funds.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String text;
	
	//private Post post;

}
