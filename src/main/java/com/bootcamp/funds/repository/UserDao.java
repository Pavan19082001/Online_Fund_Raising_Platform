package com.bootcamp.funds.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.funds.model.User;

@Component
public class UserDao {
	
	@Autowired
    UserRepository userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}

}
