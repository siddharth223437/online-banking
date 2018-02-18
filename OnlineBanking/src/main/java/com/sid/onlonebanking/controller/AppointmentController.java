package com.sid.onlonebanking.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sid.onlonebanking.repository.UserRepository;
import com.sid.onlonebanking.service.AppointmentService;
import com.sid.onlonebanking.vo.AccountResponse;
import com.sid.onlonebanking.vo.AppointmentVO;
import com.sid.onlonebanking.vo.Users;

@RestController
@RequestMapping("appointment")
@CrossOrigin
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/create/{name}/{actnum}/{date}/{reason}")
	public AccountResponse<AppointmentVO> saveAppointmentRest(@PathVariable("name") String name,@PathVariable("actnum") int act,@PathVariable("date") Date date, @PathVariable("reason") String reason,Principal principal) throws ParseException {
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		resp = appointmentService.save(name, act, date, reason, principal.getName());
		return resp;
	}
	
	@GetMapping("/find/user")
	public AccountResponse<List<AppointmentVO>> findAppointByUsername(Principal principal) {
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		resp = appointmentService.allAppointmet(principal.getName());
		return resp;
	}
	
	@GetMapping("/find/all")
	public AccountResponse<List<AppointmentVO>> findAll(Principal principal){
		AccountResponse<List<AppointmentVO>> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user.getRole().equals("ADMIN")) {
		resp = appointmentService.findAllAppointments();
		return resp;
		}
		resp.setStatus(false);
		resp.setMessage("Only Admin can access this Service");
		return resp;
	}
	@PostMapping("/find/name") // in post same name should be in angular model class
	public @ResponseBody AccountResponse<List<AppointmentVO>> findNameAndStatus(@RequestBody AppointmentVO app) throws ParseException {
		if(app.getAppointmentDate() == null) {
			AccountResponse<List<AppointmentVO>> resp = appointmentService.findByNameAndStatus(app.getName(), app.getStatus(), null);
			return resp;
		}else {
		AccountResponse<List<AppointmentVO>> resp = appointmentService.findByNameAndStatus(app.getName(), app.getStatus(), app.getAppointmentDate().toString());
		return resp;
		}
		//return resp;
	}
	
	@GetMapping("/find/{id}")
	public AccountResponse<AppointmentVO> getByAppoitmentId(Principal principal, @PathVariable("id") String id){
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user.getRole().equals("ADMIN")) {
			resp = appointmentService.findAppointmentById(id);
			return resp;
		}
		return resp;
		
	}
	
	@GetMapping("/approve/{id}")
	public AccountResponse<AppointmentVO> approveAppointment(Principal principal, @PathVariable("id") String id){
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user.getRole().equals("ADMIN")) {
			resp = appointmentService.approveAppointment(id);
		}
		return resp;
	}
	
	@GetMapping("/cancel/{id}")
	public AccountResponse<AppointmentVO> cancelAppointment(Principal principal, @PathVariable("id") String id){
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user!=null) {
			resp = appointmentService.denyAppointment(id);
		}
		return resp;
	}
	
	@GetMapping("/delete/{id}")
	public AccountResponse<AppointmentVO> deleteAppointmentById(@PathVariable("id") String id, Principal principal){
		AccountResponse<AppointmentVO> resp = new AccountResponse<>();
		Users user = userRepository.findByUsername(principal.getName());
		if(user.getRole().equals("ADMIN")) {
		resp = appointmentService.deleteAppointmentById(id);
		}
		return resp;
	}

}
