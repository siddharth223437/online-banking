package com.sid.onlonebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.PrimaryAccount;

public interface PrimaryAccountRepository extends MongoRepository<PrimaryAccount, String> {
	
	public PrimaryAccount findByaccountNumber(int actno);

}
