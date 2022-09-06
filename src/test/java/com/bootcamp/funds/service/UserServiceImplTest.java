package com.bootcamp.funds.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.UserDto;
import com.bootcamp.funds.model.User;
import com.bootcamp.funds.repository.UserDao;
import com.bootcamp.funds.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@MockBean
	UserRepository userRepo;
	
	@MockBean
	UserDao userDao;
	
	@Test
	public void addUserTest() {
		User user = createUserMockData();
		when(userDao.createUser(ArgumentMatchers.any(User.class))).thenReturn(user);
		UserDto userDto = userService.addUser(modelMapper.map(user, UserDto.class));
		assertEquals(user.getUsername() ,userDto.getUsername());
		
	}
	
	@Test
	public void showAllUsersTest() {
		List<User> user = createsUserMockData();
		when(userRepo.findAll()).thenReturn(user);
		List<UserDto> userDto = userService.showAllUsers();
		assertEquals(user.size(), userDto.size());
	}
	
	
	
	private User createUserMockData() {
		User user = new User();
		
		user.setId(1L);
		user.setUsername("Venky");
		user.setFirstName("Krishna");
		user.setLastName("Vamsi");
		user.setDateOfBirth(LocalDate.of(2000, 04, 04));
		user.setEmailId("vamsipyradipalli43@gmail.com");
		user.setPassword("Krishnavamsi@#123");
		
		return user;
	}
	
	private List<User> createsUserMockData() {
		List<User> newUser = new ArrayList<>();
		User user = new User();
		
		user.setId(1L);
		user.setUsername("Venky");
		user.setFirstName("Krishna");
		user.setLastName("Vamsi");
		user.setDateOfBirth(LocalDate.of(2000, 04, 04));
		user.setEmailId("vamsipyradipalli43@gmail.com");
		user.setPassword("Krishnavamsi@#123");
		
		newUser.add(user);
		return newUser;
	}

}
