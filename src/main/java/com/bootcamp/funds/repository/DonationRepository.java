package com.bootcamp.funds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.funds.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

	List<Donation> findByPostId(long postId);
}
