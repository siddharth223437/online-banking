package com.sid.onlonebanking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.AccountTransaction;

public interface AccountTransactionRepository extends MongoRepository<AccountTransaction, String> {
	
	public List<AccountTransaction> findByUsername(String username);
}
