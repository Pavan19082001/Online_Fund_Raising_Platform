package com.bootcamp.funds.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.funds.model.Admin;


@Component
public class AdminDao {
	
	@Autowired
    AdminRepository adminRepo;
	
	public Admin createAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

}
