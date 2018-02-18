package com.sid.onlonebanking.vo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Recipent")
public class RecipentVO {
	private String id;
	private String username;
	private String recipentEmail;
	private long recipentNumber;
	private int recipentAccountNumber;
	private String recipentName;
	
	
	public String getRecipentName() {
		return recipentName;
	}
	public void setRecipentName(String recipentName) {
		this.recipentName = recipentName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRecipentEmail() {
		return recipentEmail;
	}
	public void setRecipentEmail(String recipentEmail) {
		this.recipentEmail = recipentEmail;
	}
	public long getRecipentNumber() {
		return recipentNumber;
	}
	public void setRecipentNumber(long recipentNumber) {
		this.recipentNumber = recipentNumber;
	}
	public int getRecipentAccountNumber() {
		return recipentAccountNumber;
	}
	public void setRecipentAccountNumber(int recipentAccountNumber) {
		this.recipentAccountNumber = recipentAccountNumber;
	}
	
	

}
