package com.sid.onlonebanking.vo;

import java.util.Date;
import java.util.List;

public class PrimaryTransaction {
	private String username;
	private String PrimaryTransactionId;
	private Date date;
    private String description;
    private String type;
    private String status;
    private String amount;
    private double availableBalance;
    private Date lastViewed;
    private String newDate;

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public Date getLastViewed() {
		return lastViewed;
	}

	public void setLastViewed(Date lastViewed) {
		this.lastViewed = lastViewed;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private PrimaryAccount primaryAccount;
    

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

	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
    

}
