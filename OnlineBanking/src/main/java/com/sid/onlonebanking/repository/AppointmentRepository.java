package com.sid.onlonebanking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sid.onlonebanking.vo.AppointmentVO;

public interface AppointmentRepository extends MongoRepository<AppointmentVO, String> {
	
	public List<AppointmentVO> findByUsername(String username);
	public List<AppointmentVO> findAll();
	public List<AppointmentVO> findByNameAllIgnoreCase(String name);
	public List<AppointmentVO> findByStatusAllIgnoreCase(String status);
	public List<AppointmentVO> findBynameAndStatusAllIgnoreCase(String name, String status);
	public List<AppointmentVO> findByonlyAppointmentDate(String date);
	public List<AppointmentVO> findByonlyAppointmentDateAndNameAllIgnoreCase(String date, String name);
	public List<AppointmentVO> findByonlyAppointmentDateAndStatusAllIgnoreCase(String date, String status);
	public List<AppointmentVO> findByonlyAppointmentDateAndNameAndStatusAllIgnoreCase(String date, String name, String status);
	public AppointmentVO findById(String id);

}
