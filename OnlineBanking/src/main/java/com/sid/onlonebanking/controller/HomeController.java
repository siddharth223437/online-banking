package com.sid.onlonebanking.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sid.onlonebanking.repository.PrimaryAccountRepository;
import com.sid.onlonebanking.repository.SavingsAccountRepository;
import com.sid.onlonebanking.repository.SavingsTransactionsRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.serviceImpl.UserService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.PrimaryAccount;
import com.sid.onlonebanking.vo.SavingsAccount;
import com.sid.onlonebanking.vo.Users;

@RestController
@RequestMapping("account")
@CrossOrigin
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrimaryAccountRepository primaryActRepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private SavingsAccountRepository savingsActRepo;
	
	@Autowired
	private SavingsTransactionsRepository savingsTransactionRepo;
	
	@PostMapping("/register")
	public @ResponseBody AccountResponse<Users> createAccount(@RequestBody Users users,Principal prin){
		AccountResponse<Users> resp = new AccountResponse<>();
		Users u1 = userRepository.findByUsername(users.getUsername());
		if(u1 != null) {
			resp.setMessage("UserName already exists");
			resp.setResponseObject(users);
			resp.setStatus(false);
			return resp;
		}
		String encryptedPassword = passwordEncoder.encode(users.getPassword());
		users.setPasssword(encryptedPassword);
		String encryptedSSN = passwordEncoder.encode(users.getSsn());
		users.setSsn(encryptedSSN);
		users.setRole("USER");
		PrimaryAccount pa = new PrimaryAccount();
		Date date = new Date();
		users.setCreatedDate(date);
		pa.setAccountBalance(0);
		users.setPrimaryAccount(pa);
		users.setUserStatus("Pending");
		SavingsAccount sa = new SavingsAccount();
		sa.setAccountBalance(0);
		users.setSavingsAccount(sa);
		userService.saveUser(users);
		resp.setMessage("Thanks for Registering");
		resp.setStatus(true);
		resp.setResponseObject(users);
		return resp;
	}
	
	
	@GetMapping("/login")
	public Principal getPrincipal(Principal principal) {
		System.out.println("Name is" + principal.getName());
		Users user = userRepository.findByUsername(principal.getName());
		if(user!=null) {
			Date date = new Date();
			user.setLastLogedIn(date);
			userRepository.save(user);
		}
		return principal;
	}
	
	
	@PostMapping("/home")
	public String getName() {
		return "sid";
	}
	
	@GetMapping("/all/users/{role}")
	public AccountResponse<List<Users>> allUsers(@PathVariable("role") String role) {
		AccountResponse<List<Users>> resp = new AccountResponse<>();
		List<Users> list = userRepository.findByRole(role);
		resp.setResponseObject(list);
		resp.setStatus(true);
		return resp;
	}
	
	@GetMapping("/showsingleuser/{username}")
	public AccountResponse<Users> findUserByUsername(@PathVariable("username") String username){
		AccountResponse<Users> resp = new AccountResponse<>();
		Users users = userRepository.findByUsername(username);
		if(users!=null) {
			resp.setResponseObject(users);
			resp.setStatus(true);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}
	
	@GetMapping("/approve/{username}")
	public AccountResponse<Users> approveUser(@PathVariable("username") String username) {
		AccountResponse<Users> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			user.setUserStatus("Approved");
			user.setActive(true);
			userRepository.save(user);
			resp.setResponseObject(user);
			resp.setStatus(true);
			resp.setMessage("Status is Approved");
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}
	
	@PutMapping("/update")
	public AccountResponse<Users> update(@RequestBody Users user) {
		AccountResponse<Users> resp = new AccountResponse<>();
		if(user != null) {
			Users u = userRepository.findByUsername(user.getUsername());
			u.setUsername(user.getUsername());
			u.setfName(user.getfName());
			u.setlName(user.getlName());
			u.setAddress(user.getAddress());
			u.setCountry(user.getCountry());
			u.setState(user.getState());
			u.setCity(user.getCity());
			u.setPhoneNumber(user.getPhoneNumber());
			userRepository.save(u);
			resp.setResponseObject(u);
			resp.setStatus(true);
			resp.setMessage("Updated Successfully");
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}
	
	@GetMapping("/assign/act/{username}/{act}")
	public AccountResponse<Users> assignAccountNum(@PathVariable("username") String username,@PathVariable("act") int act){
		AccountResponse<Users> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			PrimaryAccount pa = new PrimaryAccount();
			SavingsAccount sa = new SavingsAccount();
			pa.setAccountNumber(act);
			sa.setAccountNumber(act);
			user.setPrimaryAccount(pa);
			user.setSavingsAccount(sa);
			userRepository.save(user);
			resp.setResponseObject(user);
			resp.setMessage("Account Number Added");
			resp.setStatus(true);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}
	
	@GetMapping("/deny/{username}")
	public AccountResponse<Users> denyUser(@PathVariable("username") String username){
		AccountResponse<Users> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			user.setUserStatus("Denied");
			user.setActive(false);
			userRepository.save(user);
			resp.setResponseObject(user);
			resp.setStatus(true);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}

}
