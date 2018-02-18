package com.sid.onlonebanking.vo;

import java.util.Date;

import javax.persistence.Id;

public class SavingsTransaction {
	
	private String username;
	private String PrimaryTransactionId;
	private Date date;
    private String description;
    private String type;
    private String status;
    private String amount;
    private double availableBalance;
    private SavingsAccount savingsAccount;
    
    
    
    public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	private Users user;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPrimaryTransactionId() {
		return PrimaryTransactionId;
	}

	public void setPrimaryTransactionId(String primaryTransactionId) {
		PrimaryTransactionId = primaryTransactionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
    
    

}
