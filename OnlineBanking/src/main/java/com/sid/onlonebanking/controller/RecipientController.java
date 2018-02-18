package com.sid.onlonebanking.controller;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.service.RecipentService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.PrimaryTransaction;
import com.sid.onlonebanking.vo.RecipentVO;
import com.sid.onlonebanking.vo.Users;

@RestController
@CrossOrigin
@RequestMapping("recipient")
public class RecipientController {
	
	@Autowired
	private RecipentService recipientService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/add/{email}/{name}")
	public AccountResponse<RecipentVO> addRecipient(@PathVariable("email") String email, @PathVariable("name") String name, Principal principal) {
		AccountResponse<RecipentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user.getEmail().equalsIgnoreCase(email)) {
			resp.setMessage("You cannot add yourself as Recipient");
			resp.setStatus(false);
			return resp;
		}
		List<Users> allUsers = userRepository.findAll();
		System.out.println(allUsers.size());
		Set<Users> setUser = new HashSet<>(allUsers);
		boolean checkRecpEmail = recipientService.checkRecipientEmail(email);
		if(checkRecpEmail == true) {
			resp.setStatus(false);
			resp.setMessage("Recipient is already available in contacts");
			return resp;
		}
		for(Users u : setUser) {
			if(u.getEmail().equals(email)) {
				if(email != null) {
					RecipentVO recip = recipientService.addByEmail(email, name, principal.getName());
					if(recip  !=null) {
						resp.setMessage("Recipient Added Successfull");
						resp.setResponseObject(recip);
						resp.setStatus(true);
						return resp;
					}
				}
			}
		}
		resp.setStatus(false);
		resp.setMessage("Please check the Email");
		return resp;
	}
	
	@GetMapping("/all")
	public AccountResponse<List<RecipentVO>> findAllRecip(Principal principal) {
		AccountResponse<List<RecipentVO>> resp = new AccountResponse<>();
		List<RecipentVO> list = recipientService.findRecipientWithUsername(principal.getName());
		resp.setResponseObject(list);
		resp.setStatus(true);
		return resp;
	}
	
	@GetMapping("/transfer/{from}/{toEmail}/{amount}")
	public AccountResponse<PrimaryTransaction> transferToSomeone(@PathVariable("from") String from,@PathVariable("toEmail") String toEmail, @PathVariable("amount") String amount, Principal principal){
		AccountResponse<PrimaryTransaction> resp = new AccountResponse<>();
		boolean checkRecpEmail = recipientService.checkRecipientEmail(toEmail);
		if(checkRecpEmail == true) {
			resp.setMessage("Recipient Does not Exists");
			resp.setStatus(false);
			return resp;
		}
		 resp = recipientService.transferToSomeone(from, toEmail, amount, principal.getName());
		return resp;
		
	}

}
