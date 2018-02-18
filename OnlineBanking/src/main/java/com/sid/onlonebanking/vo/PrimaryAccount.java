package com.sid.onlonebanking.vo;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="PrimaryAccount")
public class PrimaryAccount {
	@Id
	private String id;
	private String primaryAccountId;
	private int accountNumber;
	private double accountBalance;
	private List<PrimaryTransaction> primaryTransaction;
	public String getPrimaryAccountId() {
		return primaryAccountId;
	}
	public void setPrimaryAccountId(String primaryAccountId) {
		this.primaryAccountId = primaryAccountId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<PrimaryTransaction> getPrimaryTransaction() {
		return primaryTransaction;
	}
	public void setPrimaryTransaction(List<PrimaryTransaction> primaryTransaction) {
		this.primaryTransaction = primaryTransaction;
	}
	
	
	

}
