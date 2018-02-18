package com.sid.onlonebanking.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.sid.onlonebanking.repository.AppointmentRepository;
import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.service.AppointmentService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.AppointmentVO;
import com.sid.onlonebanking.vo.Users;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private MongoOperations mongo;

	@Override
	public AccountResponse<AppointmentVO> save(String name, int actNum, Date appDate, String reason, String username) throws ParseException {
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		Date d1 = new Date();
		if(appDate.before(d1)) {
			resp.setMessage("Please select future date");
			resp.setStatus(false);
			return resp;
		}
		if(user!=null) {
			AppointmentVO app = new AppointmentVO();
			String d2 = toDate(appDate);
			
			app.setOnlyAppointmentDate(d2);
			app.setName(name);
			app.setAccountNumber(actNum);
			app.setAppointmentDate(appDate);
			app.setReason(reason);
			app.setUsername(username);
			app.setStatus("Pending");
			appointmentRepository.save(app);
			resp.setResponseObject(app);
			resp.setMessage("You will be informaed once Appointment is confirmed");
			resp.setStatus(true);
			return resp;
		}
		resp.setMessage("Error in Saving");
		resp.setStatus(false);
		return resp;
	}

	@Override
	public AccountResponse<List<AppointmentVO>> allAppointmet(String username) {
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(username);
		if(user != null) {
			List<AppointmentVO> list = appointmentRepository.findByUsername(username);
			resp.setResponseObject(list);
			resp.setStatus(true);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}

	@Override
	public AccountResponse<List<AppointmentVO>> findAllAppointments() {
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		List<AppointmentVO> list = appointmentRepository.findAll();
		resp.setResponseObject(list);
		resp.setStatus(true);
		return resp;
	}

	@Override
	public AccountResponse<List<AppointmentVO>> findByRole(String role) {
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		
		return null;
	}

	@Override
	public AccountResponse<List<AppointmentVO>> findByNameAndStatus(String name, String status, String appDate) throws ParseException {
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		String formatedDate = "";
		if(appDate!=null) {
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = (Date)formatter.parse(appDate);
		System.out.println(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
		System.out.println("formatedDate : " + formatedDate);
		}else {
			formatedDate = "";
		}
		if(name!=null && (status == null || status=="") && (formatedDate == null || formatedDate=="")) {
			List<AppointmentVO> list1 = appointmentRepository.findByNameAllIgnoreCase(name);
			resp.setResponseObject(list1);
			resp.setStatus(true);
			return resp;
		}
		if(status!=null && (name==null || name == "") && (formatedDate==null || formatedDate=="")) {
			List<AppointmentVO> list2 = appointmentRepository.findByStatusAllIgnoreCase(status);
			resp.setResponseObject(list2);
			resp.setStatus(true);
			return resp;
		}
		if((name!=null) && (status!=null) && (formatedDate==null || formatedDate=="")) {
			List<AppointmentVO> list3 = appointmentRepository.findBynameAndStatusAllIgnoreCase(name, status);
			resp.setResponseObject(list3);
			resp.setStatus(true);
			return resp;
		}
		if((formatedDate!=null) && (name==null || name=="") && (status==null || status == "") ) {
			List<AppointmentVO> list3 = appointmentRepository.findByonlyAppointmentDate(formatedDate);
			resp.setResponseObject(list3);
			resp.setStatus(true);
			return resp;
		}
		if((formatedDate!=null && name!=null) && (status==null || status=="")) {
			List<AppointmentVO> list3 = appointmentRepository.findByonlyAppointmentDateAndNameAllIgnoreCase(formatedDate, name);
			resp.setResponseObject(list3);
			resp.setStatus(true);
			return resp;
		}
		if((formatedDate!=null && status!=null) && (name==null || name=="")) {
			List<AppointmentVO> list3 = appointmentRepository.findByonlyAppointmentDateAndNameAllIgnoreCase(formatedDate, status);
			resp.setResponseObject(list3);
			resp.setStatus(true);
			return resp;
		}
		if((formatedDate!=null && status!=null && name!=null)) {
			List<AppointmentVO> list3 = appointmentRepository.findByonlyAppointmentDateAndNameAndStatusAllIgnoreCase(formatedDate, name, status);
			resp.setResponseObject(list3);
			resp.setStatus(true);
			return resp;
		}
		resp.setMessage("No Results Found");
		resp.setStatus(false);
		return resp;
		
	}
	
	private String toDate(Date appDate) throws ParseException {
//		String dateStr = appDate.toString();
//		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//		Date date = (Date)formatter.parse(dateStr);
//		System.out.println(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(appDate);
		String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
		System.out.println("formatedDate : " + formatedDate);    
		return formatedDate;
	}

	@Override
	public AccountResponse<AppointmentVO> findAppointmentById(String id) {
		AppointmentVO app = appointmentRepository.findById(id);
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		resp.setResponseObject(app);
		resp.setStatus(true);
		return resp;
	}

	@Override
	public AccountResponse<AppointmentVO> approveAppointment(String id) {
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		AppointmentVO appointment = appointmentRepository.findById(id);
		if(appointment!=null) {
			appointment.setStatus("Approved");
			resp.setResponseObject(appointment);
			resp.setMessage("Appintment is Scheduled");
			resp.setStatus(true);
			appointmentRepository.save(appointment);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}

	@Override
	public AccountResponse<AppointmentVO> denyAppointment(String id) {
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		AppointmentVO appointment = appointmentRepository.findById(id);
		if(appointment!=null) {
			appointment.setStatus("Cancelled");
			resp.setResponseObject(appointment);
			resp.setMessage("Appintment is Cancelled");
			resp.setStatus(true);
			appointmentRepository.save(appointment);
			return resp;
		}
		resp.setStatus(false);
		return resp;
	}

	@Override
	public AccountResponse<AppointmentVO> deleteAppointmentById(String id) {
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		AppointmentVO app = appointmentRepository.findById(id);
		mongo.remove(app);
		resp.setMessage("Appointment deleted");
		resp.setStatus(true);
		return resp;
	}
	
	
	

}
