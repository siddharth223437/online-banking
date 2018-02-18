package com.sid.onlonebanking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.RecipentVO;
import com.sid.onlonebanking.vo.Users;

public interface RecpientRepository extends MongoRepository<RecipentVO, String> {
	
	public List<RecipentVO> findByUsername(String username);
	public RecipentVO findByrecipentEmail(String email);

}
