package com.sid.onlonebanking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.CountrySelectVO;

public interface CountrySelectRepository extends MongoRepository<CountrySelectVO, String> {

}
