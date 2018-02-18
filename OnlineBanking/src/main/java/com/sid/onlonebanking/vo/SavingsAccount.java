package com.sid.onlonebanking.vo;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="PrimaryAccount")
public class SavingsAccount {
	
	@Id
	private String id;
	private String primaryAccountId;
	private int accountNumber;
	private double accountBalance;
	private List<SavingsTransaction> savingTransaction;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrimaryAccountId() {
		return primaryAccountId;
	}
	public void setPrimaryAccountId(String primaryAccountId) {
		this.primaryAccountId = primaryAccountId;
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
	public List<SavingsTransaction> getSavingTransaction() {
		return savingTransaction;
	}
	public void setSavingTransaction(List<SavingsTransaction> savingTransaction) {
		this.savingTransaction = savingTransaction;
	}
	
	

}
