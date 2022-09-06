package com.bootcamp.funds.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.bootcamp.funds.dto.UserDto;
import com.bootcamp.funds.exceptions.UserNotFoundException;
import com.bootcamp.funds.service.UserService;

@RestController
@RequestMapping("/userapi/v1")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		logger.info("UserController::getAllUsers::Entered");
		return new ResponseEntity<List<UserDto>>(userService.showAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
		logger.info("User "+user.getUsername()+" created successfully");
		
		return new ResponseEntity<UserDto>(userService.addUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto user){
		UserDto opt = userService.getUserByName(username);
		if(opt == null) {
			throw new UserNotFoundException();
		}
		UserDto u = userService.updateUser(username, user);
		return new ResponseEntity<UserDto>(u, HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable String username){
		return new ResponseEntity<UserDto>(userService.getUserByName(username), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{username}")
	public ResponseEntity<UserDto> removeUser(@PathVariable String username){
		logger.error("User "+username+" is deleted");
		userService.deleteUser(username);
		return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
	

}
