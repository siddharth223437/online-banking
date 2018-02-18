package com.sid.onlonebanking.vo;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="AccountTransaction")
public class AccountTransaction {
	@Id
	private String id;
	private PrimaryTransaction primaryTransaction;
	private SavingsTransaction savingsTransaction;
	private Users user;
	private String username;
	private Date createdDate;
	
	
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public PrimaryTransaction getPrimaryTransaction() {
		return primaryTransaction;
	}
	public void setPrimaryTransaction(PrimaryTransaction primaryTransaction) {
		this.primaryTransaction = primaryTransaction;
	}
	public SavingsTransaction getSavingsTransaction() {
		return savingsTransaction;
	}
	public void setSavingsTransaction(SavingsTransaction savingsTransaction) {
		this.savingsTransaction = savingsTransaction;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
