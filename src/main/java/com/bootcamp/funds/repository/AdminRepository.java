package com.bootcamp.funds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bootcamp.funds.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("select a from Admin a where a.adminName=:adminname")
	Optional<Admin> findAdminByName(String adminname);

	@Query("select a from Admin a where a.adminName=:adminname")
	Admin deleteAdminByName(String adminname);

}
