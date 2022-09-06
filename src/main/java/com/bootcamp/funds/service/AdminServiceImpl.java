package com.bootcamp.funds.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.funds.dto.AdminDto;
import com.bootcamp.funds.exceptions.AdminNotFoundException;
import com.bootcamp.funds.model.Admin;
import com.bootcamp.funds.repository.AdminDao;
import com.bootcamp.funds.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository repo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AdminDao dao;

	@Override
	public List<AdminDto> getAllAdmins() {
		List<Admin> admins = repo.findAll();
		List<AdminDto> adminDTO = admins.stream().map(admin -> modelMapper.map(admin, AdminDto.class))
				.collect(Collectors.toList());
		return adminDTO;
	}

	@Override
	public AdminDto getAdminByName(String adminname) {
		Optional<Admin> opt = repo.findAdminByName(adminname);
		if (!opt.isPresent()) {
			throw new AdminNotFoundException();
		}
		return modelMapper.map(opt.get(), AdminDto.class);
	}

	@Override
	public AdminDto addAdmin(AdminDto dto) {
		Admin admin = modelMapper.map(dto, Admin.class);
		admin = dao.createAdmin(admin);
		return modelMapper.map(admin, AdminDto.class);
	}

	@Override
	public AdminDto updateAdmin(String adminname, AdminDto dto) {
		repo.deleteAdminByName(adminname);
		return addAdmin(dto);
	}

	@Override
	public String deleteAdmin(String adminname) {
		Optional<Admin> opt = repo.findAdminByName(adminname);
		if (!opt.isPresent()) {
			throw new AdminNotFoundException();
		}
		repo.deleteAdminByName(adminname);
		return "Admin :: " + adminname + " is deleted successfully";
	}

}
