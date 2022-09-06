package com.bootcamp.funds.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bootcamp.funds.dto.DonationDto;
import com.bootcamp.funds.exceptions.APIException;
import com.bootcamp.funds.exceptions.DonationNotFoundException;
import com.bootcamp.funds.exceptions.PostNotFoundException;
import com.bootcamp.funds.model.Donation;
import com.bootcamp.funds.model.Post;
import com.bootcamp.funds.repository.DonationRepository;
import com.bootcamp.funds.repository.PostRepository;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	DonationRepository donationRepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	PostRepository postRepo;

	@Override
	public List<DonationDto> viewAllDonations(long postId) {
		List<Donation> donationDemo = donationRepo.findByPostId(postId);

		return donationDemo.stream().map(donation -> mapper.map(donation, DonationDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DonationDto viewDonation(long postId, long donationId) {
		// retrieve post entity by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		// retrieve donations by donationId
		Donation donation = donationRepo.findById(donationId).orElseThrow(() -> new DonationNotFoundException());

		if (!(donation.getPost().getId() == post.getId())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "donations doesnot belongs to the post");
		}

		return mapper.map(donation, DonationDto.class);
	}

	@Override
	public DonationDto donate(long postId, DonationDto donationDto) {
		Donation donation = mapper.map(donationDto, Donation.class);

		// retrieve post entity by id
		Post post = postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException());

		donation.setPost(post);

		Donation newDonation = donationRepo.save(donation);

		return mapper.map(newDonation, DonationDto.class);
	}

}
