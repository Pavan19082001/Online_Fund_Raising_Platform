package com.bootcamp.funds.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.DonationDto;
import com.bootcamp.funds.model.Donation;
import com.bootcamp.funds.repository.DonationRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DonationServiceImplTest {

	@Autowired
	DonationServiceImpl donationService;

	@Autowired
	ModelMapper modelMapper;

	@MockBean
	DonationRepository donationRepository;

	@Test
	public void viewAllDonationsTest() {
		List<Donation> donation = createDonationsDtoMockData();
		when(donationRepository.findAll()).thenReturn(donation);

		List<DonationDto> response = donationService.viewAllDonations(1L);

		assertEquals(donation.size(), response.size());
	}

	private List<Donation> createDonationsDtoMockData() {
		List<Donation> donations = new ArrayList<>();

		Donation donation = new Donation();
		donation.setId(10L);
		donation.setAmount(26000);
		
		donations.add(donation);

		return donations;
	}

}

