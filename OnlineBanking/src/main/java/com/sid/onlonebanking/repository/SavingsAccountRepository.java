package com.sid.onlonebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.SavingsAccount;

public interface SavingsAccountRepository extends MongoRepository<SavingsAccount, String> {

}
