package com.sid.onlonebanking.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.vo.Users;


@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
			if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return user;
	}

}
