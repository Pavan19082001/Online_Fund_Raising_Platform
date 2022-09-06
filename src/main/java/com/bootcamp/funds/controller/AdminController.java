package com.bootcamp.funds.controller;

import java.util.List;

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

import com.bootcamp.funds.dto.AdminDto;
import com.bootcamp.funds.exceptions.AdminNotFoundException;
import com.bootcamp.funds.service.AdminService;

@RestController
@RequestMapping("/adminapi/v1")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/admins")
	public ResponseEntity<List<AdminDto>> showAllAdmins(){
		return new ResponseEntity<List<AdminDto>>(adminService.getAllAdmins(), HttpStatus.OK);
	}
	
	@PostMapping("/admins")
	public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto admin){
		return new ResponseEntity<AdminDto>(adminService.addAdmin(admin), HttpStatus.CREATED);
	}
	
	@PutMapping("/admins/{adminname}")
	public ResponseEntity<AdminDto> updateAdmin(@PathVariable String adminname, @RequestBody AdminDto admin){
		AdminDto opt = adminService.getAdminByName(adminname);
		if(opt == null) {
			throw new AdminNotFoundException();
		}
		AdminDto u = adminService.updateAdmin(adminname, admin);
		return new ResponseEntity<AdminDto>(u, HttpStatus.CREATED);
	}
	
	@GetMapping("/admins/{adminname}")
	public ResponseEntity<AdminDto> getAdmin(@PathVariable String adminname){
		return new ResponseEntity<AdminDto>(adminService.getAdminByName(adminname), HttpStatus.OK);
	}
	
	@DeleteMapping("/admins/{adminname}")
	public ResponseEntity<AdminDto> removeAdmin(@PathVariable String adminname){
		adminService.deleteAdmin(adminname);
		return new ResponseEntity<AdminDto>(HttpStatus.NO_CONTENT);
	}
}
