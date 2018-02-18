package com.sid.onlonebanking.vo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Appointment")
public class AppointmentVO {

	private String id;
	private String username;
	private int accountNumber;
	private String name;
	private Date appointmentDate;
	private String onlyAppointmentDate;
	private String reason;
	private String status;
	
	
	
	public String getOnlyAppointmentDate() {
		return onlyAppointmentDate;
	}
	public void setOnlyAppointmentDate(String onlyAppointmentDate) {
		this.onlyAppointmentDate = onlyAppointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
