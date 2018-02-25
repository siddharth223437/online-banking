package com.sid.onlonebanking.vo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="GenerateStatements")
public class GenerateReport {
	
	@Id
	private String _id;
	private String fromDate;
	private String toDate;
	private String username;
	

}
