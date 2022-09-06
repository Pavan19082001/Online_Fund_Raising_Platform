package com.bootcamp.funds.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.bootcamp.funds.dto.AdminDto;
import com.bootcamp.funds.model.Admin;
import com.bootcamp.funds.repository.AdminDao;
import com.bootcamp.funds.repository.AdminRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceImplTest {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@MockBean
	AdminRepository adminRepo;
	
	@MockBean
	AdminDao adminDao;
	
	@Test
	public void addAdminTest() {
		Admin admin = createAdminMockData();
		when(adminDao.createAdmin(ArgumentMatchers.any(Admin.class))).thenReturn(admin);
		AdminDto adminDto = adminService.addAdmin(modelMapper.map(admin, AdminDto.class));
		assertEquals(admin.getAdminName() ,adminDto.getAdminName());
	}
	
	private Admin createAdminMockData() {
		Admin admin = new Admin();
		
		admin.setAdminId(1L);
		admin.setAdminName("Deepak");
		admin.setEmailId("d@gmail.com");
		admin.setPassword("123");
		
		return admin;
		
	}

	@Test
	public void showAllAdminsTest() {
		List<Admin> admin = createsAdminMockData();
		when(adminRepo.findAll()).thenReturn(admin);
		List<AdminDto> adminDto = adminService.getAllAdmins();
		assertEquals(admin.size(), adminDto.size());
	}

	private List<Admin> createsAdminMockData() {
		List<Admin> newAdmin =  new ArrayList<>();
		Admin admin = new Admin();
		
		admin.setAdminId(1);
		admin.setAdminName("Deepak");
		admin.setEmailId("d@gmail.com");
		admin.setPassword("123");
		
		newAdmin.add(admin);
		return newAdmin;
		
	}

}
