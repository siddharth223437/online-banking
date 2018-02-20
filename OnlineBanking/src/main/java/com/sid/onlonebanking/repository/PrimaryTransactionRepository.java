package com.sid.onlonebanking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.PrimaryTransaction;

public interface PrimaryTransactionRepository extends MongoRepository<PrimaryTransaction, String> {

	public List<PrimaryTransaction> findByusername(String username);
	public List<PrimaryTransaction> findByusernameAndDateBetween(String username,Date fromDate, Date toDate);
}
