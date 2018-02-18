package com.sid.onlonebanking.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.AppointmentVO;

public interface AppointmentService {
	
	public AccountResponse<AppointmentVO> save(String name, int actNum, Date appDate, String reason, String username) throws ParseException;
	public AccountResponse<List<AppointmentVO>> allAppointmet(String username);
	public AccountResponse<List<AppointmentVO>> findAllAppointments();
	public AccountResponse<List<AppointmentVO>> findByRole(String role);
	public AccountResponse<List<AppointmentVO>> findByNameAndStatus(String name, String status, String date) throws ParseException;
	public AccountResponse<AppointmentVO> findAppointmentById(String id);
	public AccountResponse<AppointmentVO> approveAppointment(String id);
	public AccountResponse<AppointmentVO> denyAppointment(String id);
	public AccountResponse<AppointmentVO> deleteAppointmentById(String id);

}
