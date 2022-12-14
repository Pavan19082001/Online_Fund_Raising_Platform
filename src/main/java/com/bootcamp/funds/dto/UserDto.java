package com.bootcamp.funds.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.bootcamp.funds.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter  @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String emailId;
	private String password;
	
	private Set<Post> post = new HashSet<>();
	
}
