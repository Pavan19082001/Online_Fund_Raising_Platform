package com.bootcamp.funds.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootcamp.funds.dto.DonationDto;
import com.bootcamp.funds.service.DonationService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DonationControllerTest {

	@Autowired
	DonationController donationController;

	@MockBean
	DonationService donationService;

	@Test
	public void showAllDonation() {

		List<DonationDto> donation = createDonationsDtoMockData();
		when(donationService.viewAllDonations(1L)).thenReturn(donation);

		ResponseEntity<List<DonationDto>> response = donationController.showAllDonations(1L);

		assert (donation.size() == response.getBody().size());

	}

	@Test
	public void viewDonationById() {
		DonationDto donation = createDonationDtoMockData();

		ResponseEntity<DonationDto> dto = new ResponseEntity<>(donation, HttpStatus.OK);
		when(donationService.viewDonation(1L, 2L)).thenReturn(donation);

		ResponseEntity<DonationDto> donationInfo = donationController.viewDonationById(1L, donation.getId());
		assert (donationInfo.equals(dto));
	}

	private DonationDto createDonationDtoMockData() {
		DonationDto dto = new DonationDto();

		dto.setId(2L);
		dto.setAmount(40000.0);
		return dto;
	}

	private List<DonationDto> createDonationsDtoMockData() {
		List<DonationDto> donations = new ArrayList<>();

		DonationDto donation = new DonationDto();
		donation.setAmount(90000);
		donations.add(donation);
		return donations;
	}

}
