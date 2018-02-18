package com.sid.onlonebanking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.SavingsTransaction;

public interface SavingsTransactionsRepository extends MongoRepository<SavingsTransaction, String> {
	
	public List<SavingsTransaction> findByUsername(String username);

}
