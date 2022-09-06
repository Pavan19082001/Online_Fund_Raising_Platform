package com.bootcamp.funds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.funds.dto.UserDto;
import com.bootcamp.funds.exceptions.UserNotFoundException;
import com.bootcamp.funds.model.User;
import com.bootcamp.funds.repository.UserDao;
import com.bootcamp.funds.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserDao dao;

	@Override
	public UserDto addUser(UserDto dto) {
		User user = modelMapper.map(dto, User.class);
		return modelMapper.map(dao.createUser(user), UserDto.class);
	}

	@Override
	public UserDto updateUser(String username, UserDto dto) {
		deleteUser(username);
		return addUser(dto);
	}

	@Override
	public List<UserDto> showAllUsers() {
		List<User> user = repo.findAll();
		
		List<UserDto> userDto = new ArrayList<>();
		for(User u: user) {
			userDto.add(modelMapper.map(u, UserDto.class));
		}
		
		return userDto;
		
	}

	@Override
	public String deleteUser(String username) {
		Optional<User> opt = repo.findUserByUserName(username);
		if(!opt.isPresent()) {
			throw new UserNotFoundException();
		}
		repo.delete(opt.get());
		return "User :: "+username+" is deleted successfully";
	}
	
	@Override
	public UserDto getUserByName(String username) {
		
		User user = repo.findUserByUserName(username).orElseThrow(() -> new UserNotFoundException());
		
		return modelMapper.map(user, UserDto.class);
	}

}
