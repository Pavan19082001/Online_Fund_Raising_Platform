package com.bootcamp.funds.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.UserDto;
import com.bootcamp.funds.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {
	
	@Autowired
	UserController userController;
	
	@MockBean
	UserService userService;
	
	@Test
	public void getAllUsersTest() {
		
		List<UserDto> user = createUsersDtoMockData();
		when(userService.showAllUsers()).thenReturn(user);
		ResponseEntity<List<UserDto>> response = userController.getAllUsers();
		assert(user.size() == response.getBody().size());
	}
	
	@Test
	public void createUserTest() {
		UserDto user = createUserDtoMockData();
		when(userService.addUser(user)).thenReturn(user);
		ResponseEntity<UserDto> res = userController.createUser(user);
		assertEquals(user.getUsername(), res.getBody().getUsername());
	}
	
	@Test
	public void getUserTest() {
		UserDto user = createUserDtoMockData();
		when(userService.getUserByName(user.getUsername())).thenReturn(user);
		ResponseEntity<UserDto> response = userController.getUser(user.getUsername());
		assertNotSame(user, response);
	}
	
	@Test
	public void deleteUserTest() {
		UserDto user = createUserDtoMockData();
		when(userService.deleteUser(user.getUsername())).thenReturn(null);
		ResponseEntity<UserDto> response = userController.removeUser(user.getUsername());
		assertNotEquals(user, response);
	}
	
	private List<UserDto> createUsersDtoMockData(){
		List<UserDto> users = new ArrayList<>();
		
		UserDto user = new UserDto();
		user.setUsername("Kanna");
		user.setFirstName("Krishna");
		user.setLastName("Vamsi");
		user.setDateOfBirth(LocalDate.of(2000, 04, 04));
		user.setEmailId("vamsipyradipalli43@gmail.com");
		user.setPassword("Krishnavamsi@#123");
		users.add(user);
		
		return users;
	}
	
	private UserDto createUserDtoMockData() {
		UserDto user = new UserDto();
		user.setUsername("Kanna");
		user.setFirstName("Krishna");
		user.setLastName("Vamsi");
		user.setDateOfBirth(LocalDate.of(2000, 04, 04));
		user.setEmailId("vamsipyradipalli43@gmail.com");
		user.setPassword("Krishnavamsi@#123");
		
		return user;
	}

}
