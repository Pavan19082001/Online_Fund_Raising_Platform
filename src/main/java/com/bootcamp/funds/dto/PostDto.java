package com.bootcamp.funds.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bootcamp.funds.model.Comment;
import com.bootcamp.funds.model.Donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter   @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String title;
	private String description;
	
	//private User user;
	
	private Set<Comment> commentList = new HashSet<Comment>();
	
	private List<Donation> donationsList = new ArrayList<Donation>();

}
