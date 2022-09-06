package com.bootcamp.funds.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.AdminDto;
import com.bootcamp.funds.service.AdminService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminControllerTest {

	@Autowired
	AdminController adminController;

	@MockBean
	AdminService adminService;

	@Test
	public void showAllAdminsTest() {

		List<AdminDto> admins = createAdminsDtoMockData();
		when(adminService.getAllAdmins()).thenReturn(admins);
		ResponseEntity<List<AdminDto>> response = adminController.showAllAdmins();
		assert (admins.size() == response.getBody().size());
	}

	@Test
	public void createAdminTest() {

		AdminDto admin = createAdminDtoMockData();
		when(adminService.addAdmin(admin)).thenReturn(admin);
		ResponseEntity<AdminDto> response = adminController.createAdmin(admin);
		assertEquals(admin.getAdminName(), response.getBody().getAdminName());
	}

	@Test
	public void getAdminTest() {

		AdminDto admin = createAdminDtoMockData();
		when(adminService.getAdminByName(admin.getAdminName())).thenReturn(admin);
		ResponseEntity<AdminDto> response = adminController.getAdmin(admin.getAdminName());
		assertNotSame(admin, response);
	}

	@Test
	public void deleteAdminTest() {

		AdminDto admin = createAdminDtoMockData();
		when(adminService.deleteAdmin(admin.getAdminName())).thenReturn(null);
		ResponseEntity<AdminDto> response = adminController.removeAdmin(admin.getAdminName());
		assertNotEquals(admin, response);
	}

	private List<AdminDto> createAdminsDtoMockData() {
		List<AdminDto> admins = new ArrayList<>();

		AdminDto admin = new AdminDto();
		admin.setAdminName("Deepak");
		admin.setEmailId("d@gmail.com");
		admin.setPassword("123");

		admins.add(admin);

		return admins;
	}

	private AdminDto createAdminDtoMockData() {

		AdminDto admin = new AdminDto();
		admin.setAdminName("Deepak");
		admin.setEmailId("d@gmail.com");
		admin.setPassword("123");

		return admin;
	}
}
