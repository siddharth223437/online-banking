package com.sid.onlonebanking.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.onlonebanking.repository.CountrySelectRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.serviceImpl.UserService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.CountrySelectVO;



@RestController
@CrossOrigin
@RequestMapping("world")
public class SelectCountryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountrySelectRepository countrySelectRepo;
	
	@GetMapping("/country")
	public AccountResponse<List<CountrySelectVO>> getCountries() {
		AccountResponse<List<CountrySelectVO>> resp = new AccountResponse<>();
		List<CountrySelectVO> li = countrySelectRepo.findAll();
		resp.setResponseObject(li);
		return resp;
	}
	
}
