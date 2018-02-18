package com.sid.onlonebanking.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.Users;

public interface UserRepository extends MongoRepository<Users, String> {
	
	public Users findByUsername(String username);
	public Users findByUsernameAndPassword(String username,String password);
	public List<Users> findByRole(String role);
	public List<Users> findAll();
	public Users findByEmail(String email);
}
