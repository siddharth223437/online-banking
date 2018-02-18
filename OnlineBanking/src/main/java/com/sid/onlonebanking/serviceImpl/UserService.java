package com.sid.onlonebanking.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.vo.Users;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}
	
	public Users findUserByUserName(String userName) {
		return userRepository.findOne(userName);
	}
	
	public Users findUserById(String id) {
		return userRepository.findOne(id);
	}

}
