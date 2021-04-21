package com.demo.Accommodation_Complaints_Feedback_System.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.Accommodation_Complaints_Feedback_System.dao.ServiceHalls;
import com.demo.Accommodation_Complaints_Feedback_System.model.User;
import com.demo.Accommodation_Complaints_Feedback_System.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.demo.Accommodation_Complaints_Feedback_System.model.Complaint;
import com.demo.Accommodation_Complaints_Feedback_System.model.Hostel;
import com.demo.Accommodation_Complaints_Feedback_System.model.Report;

@Controller
public class HallsController {
	
	@Autowired
	ServiceHalls service;
	
	@Autowired
	UserRepository user_repository;
	
	
	@RequestMapping("/")
	public String getUser() {
		return "login.jsp";
	}
	
	//register new user
	@PostMapping("/registerUser")
	public String addUser(@RequestParam String user_number, @RequestParam String user_firstname, @RequestParam String user_lastname, 
			@RequestParam String username, @RequestParam String user_email, @RequestParam String user_role, @RequestParam String user_hostel, 
			@RequestParam String user_block, @RequestParam String user_room_number, @RequestParam String bedNo,
			Model model) {
		
		User user1=service.getUser(user_number);
		if(user1==null) {
			User user = new User();
			
			user.setUserNumber(user_number);
			user.setUser_firstname(user_firstname);
			user.setUser_lastname(user_lastname);
			user.setUsername(username);
			user.setUser_email(user_email);
			user.setUserRole(user_role);
			user.setUser_hostel(user_hostel);
			user.setUser_block(user_block);
			user.setUser_room_number(user_room_number);
			user.setPassword(user_number);
			
			
			service.saveUser(user);
			String message1="User successfully registered";
			model.addAttribute("message", message1);
			
		if(user.getUserRole().equals("student")) {
			Hostel hostel=service.getHostelAndBedNo(user_hostel, user_block, user_room_number, bedNo);
			
			hostel.setVacancy(user_number);
			return "registerStudent.jsp";	
		}
		else if(user.getUserRole().equals("custodian")) {
			return "registerCustodian.jsp";	
		}
		else{
			return "register_user.jsp";	
		}
		}
		else if(user1!=null && user1.getUserRole().equals("student")) {
			String message1="Registration unsuccessful, Student already registered";
			model.addAttribute("message", message1);
			return "registerStudent.jsp";	
		}
		else if(user1!=null && user1.getUserRole().equals("custodian")) {
			String message1="Registration unsuccessful,Staff already registered, try again";
			model.addAttribute("message", message1);
			return "registerCustodian.jsp";	
		}
		else
		{	
			String message1="Registration unsuccessful,Staff already registered, try again";
			model.addAttribute("message", message1);
			return "register_user.jsp";	
		}
		
		
	}
	
	//delete user
	@RequestMapping(value="/users.jsp/delete/{userId}", method=RequestMethod.GET)
	public String delete(@PathVariable("userId") int userId, Map<String, Object> map) {
		service.deleteUser(userId);
		return "redirect:/admin/users.jsp";
	
	}
	
	//login into platform
	@PostMapping("/login")
	public String validate(@RequestParam String user_role, @RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request, HttpSession session){	
		   
		 switch (user_role) {  
			 case "admin":  
				User admin = service.getUser(username, password);
				if(admin!=null && admin.getUserRole().equals("admin")) {
					
					//Save Sessions
					@SuppressWarnings("unchecked")
					ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
					@SuppressWarnings("unchecked")
					ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
					@SuppressWarnings("unchecked")
					ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
					@SuppressWarnings("unchecked")
					ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
					@SuppressWarnings("unchecked")
					ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
					
								
					if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
						user_id = new ArrayList<>();
						user_firstname = new ArrayList<>();
						user_lastname = new ArrayList<>();
						user_number = new ArrayList<>();
						user_email = new ArrayList<>();
						
						request.getSession().setAttribute("USER_ID", user_id);
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
						request.getSession().setAttribute("USER_LASTNAME", user_lastname);
						request.getSession().setAttribute("USER_NUMBER", user_number);
						request.getSession().setAttribute("USER_EMAIL", user_email);
						
					}
					
					user_id.add(admin.getUser_id());
					user_firstname.add(admin.getUser_firstname());
					user_lastname.add(admin.getUser_lastname());
					user_number.add(admin.getUserNumber());
					user_email.add(admin.getUser_email());
					
					request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
					request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
					request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
					request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
					request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
					if (password.equals(admin.getUserNumber())) {
						return "redirect:/profile.jsp";
					} else {	
					return "redirect:/adminUI.jsp";
					}
				} else {
					return "redirect:/login.jsp";
				}
				
			 case "student":  
					User student = service.getUser(username, password);
					if(student!=null && student.getUserRole().equals("student")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_hostel = (ArrayList<Object>) request.getSession().getAttribute("USER_HOSTEL");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_block = (ArrayList<Object>) request.getSession().getAttribute("USER_BLOCK");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_room_number= (ArrayList<Object>) request.getSession().getAttribute("USER_ROOM_NUMBER");
						
									
						if (user_id == null ||  user_firstname == null  || user_lastname == null ||  user_number == null || user_email == null ||  user_hostel == null ||  user_block == null || user_room_number == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							user_hostel = new ArrayList<>();
							user_block = new ArrayList<>();
							user_room_number = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							request.getSession().setAttribute("USER_HOSTEL", user_hostel);
							request.getSession().setAttribute("USER_BLOCK", user_block);
							request.getSession().setAttribute("USER_ROOM_NUMBER", user_room_number);
							
						}
						
						user_id.add(student.getUser_id());
						user_firstname.add(student.getUser_firstname());
						user_lastname.add(student.getUser_lastname());
						user_number.add(student.getUserNumber());
						user_email.add(student.getUser_email());
						user_hostel.add(student.getUser_hostel());
						user_block.add(student.getUser_block());
						user_room_number.add(student.getUser_room_number());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_HOSTEL", user_hostel.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_BLOCK", user_block.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_ROOM_NUMBER", user_room_number.toString().replace("[", "").replace("]", ""));
						if (password.equals(student.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/studentUI.jsp";
						}		
					} else {
						return "redirect:/login.jsp";
					}
				
			 case "halls_officer":  
					User halls_officer = service.getUser(username, password);
					if(halls_officer!=null && halls_officer.getUserRole().equals("halls_officer")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(halls_officer.getUser_id());
						user_firstname.add(halls_officer.getUser_firstname());
						user_lastname.add(halls_officer.getUser_lastname());
						user_number.add(halls_officer.getUserNumber());
						user_email.add(halls_officer.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						
						if (password.equals(halls_officer.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/hallsOfficerUI.jsp";
						}	
					} else {
						return "redirect:/login.jsp";
					}
				
			 case "custodian":  
					User custodian = service.getUser(username, password);
					if(custodian!=null && custodian.getUserRole().equals("custodian")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_hostel = (ArrayList<Object>) request.getSession().getAttribute("USER_HOSTEL");
						
						
									
						if (user_id == null ||  user_firstname == null  || user_lastname == null ||  user_number == null || user_email == null ||  user_hostel == null ) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							user_hostel = new ArrayList<>();
							
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							request.getSession().setAttribute("USER_HOSTEL", user_hostel);
							
						}
						
						user_id.add(custodian.getUser_id());
						user_firstname.add(custodian.getUser_firstname());
						user_lastname.add(custodian.getUser_lastname());
						user_number.add(custodian.getUserNumber());
						user_email.add(custodian.getUser_email());
						user_hostel.add(custodian.getUser_hostel());
						
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_HOSTEL", user_hostel.toString().replace("[", "").replace("]", ""));
						
						if (password.equals(custodian.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/custodianUI.jsp";
						}	
					} else {
						return "redirect:/login.jsp";
					}	
					
					
			 case "plumber":  
					User plumber = service.getUser(username, password);
					if(plumber!=null && plumber.getUserRole().equals("plumber")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(plumber.getUser_id());
						user_firstname.add(plumber.getUser_firstname());
						user_lastname.add(plumber.getUser_lastname());
						user_number.add(plumber.getUserNumber());
						user_email.add(plumber.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(plumber.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/plumberUI.jsp";
						}	
					} else {
						return "redirect:/login.jsp";
					}	
					
			 case "mason":  
					User mason = service.getUser(username, password);
					if(mason!=null && mason.getUserRole().equals("mason")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(mason.getUser_id());
						user_firstname.add(mason.getUser_firstname());
						user_lastname.add(mason.getUser_lastname());
						user_number.add(mason.getUserNumber());
						user_email.add(mason.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(mason.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/masonUI.jsp";
						}	
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "carpenter":  
					User carpenter = service.getUser(username, password);
					if(carpenter!=null && carpenter.getUserRole().equals("carpenter")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(carpenter.getUser_id());
						user_firstname.add(carpenter.getUser_firstname());
						user_lastname.add(carpenter.getUser_lastname());
						user_number.add(carpenter.getUserNumber());
						user_email.add(carpenter.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(carpenter.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/carpenterUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "security":  
					User security = service.getUser(username, password);
					if(security!=null && security.getUserRole().equals("security")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(security.getUser_id());
						user_firstname.add(security.getUser_firstname());
						user_lastname.add(security.getUser_lastname());
						user_number.add(security.getUserNumber());
						user_email.add(security.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(security.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/securityUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "electrician":  
					User electrician = service.getUser(username, password);
					if(electrician!=null && electrician.getUserRole().equals("electrician")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(electrician.getUser_id());
						user_firstname.add(electrician.getUser_firstname());
						user_lastname.add(electrician.getUser_lastname());
						user_number.add(electrician.getUserNumber());
						user_email.add(electrician.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(electrician.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/electricianUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "cleaner":  
					User cleaner = service.getUser(username, password);
					if(cleaner!=null && cleaner.getUserRole().equals("cleaner")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(cleaner.getUser_id());
						user_firstname.add(cleaner.getUser_firstname());
						user_lastname.add(cleaner.getUser_lastname());
						user_number.add(cleaner.getUserNumber());
						user_email.add(cleaner.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(cleaner.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/cleanerUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "health":  
					User health = service.getUser(username, password);
					if(health!=null && health.getUserRole().equals("health")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(health.getUser_id());
						user_firstname.add(health.getUser_firstname());
						user_lastname.add(health.getUser_lastname());
						user_number.add(health.getUserNumber());
						user_email.add(health.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(health.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/healthUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			 case "painter":  
					User painter = service.getUser(username, password);
					if(painter!=null && painter.getUserRole().equals("painter")) {
						
						//Save Sessions
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_id = (ArrayList<Object>) request.getSession().getAttribute("USER_ID");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_firstname = (ArrayList<Object>) request.getSession().getAttribute("USER_FIRSTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_lastname = (ArrayList<Object>) request.getSession().getAttribute("USER_LASTNAME");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_number = (ArrayList<Object>) request.getSession().getAttribute("USER_NUMBER");
						@SuppressWarnings("unchecked")
						ArrayList<Object> user_email = (ArrayList<Object>) request.getSession().getAttribute("USER_EMAIL");
						
									
						if (user_id == null ||  user_firstname == null || user_lastname == null ||  user_number == null || user_email == null) {
							user_id = new ArrayList<>();
							user_firstname = new ArrayList<>();
							user_lastname = new ArrayList<>();
							user_number = new ArrayList<>();
							user_email = new ArrayList<>();
							
							request.getSession().setAttribute("USER_ID", user_id);
							request.getSession().setAttribute("USER_FIRSTNAME", user_firstname);
							request.getSession().setAttribute("USER_LASTNAME", user_lastname);
							request.getSession().setAttribute("USER_NUMBER", user_number);
							request.getSession().setAttribute("USER_EMAIL", user_email);
							
						}
						
						user_id.add(painter.getUser_id());
						user_firstname.add(painter.getUser_firstname());
						user_lastname.add(painter.getUser_lastname());
						user_number.add(painter.getUserNumber());
						user_email.add(painter.getUser_email());
						
						request.getSession().setAttribute("USER_ID", user_id.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_FIRSTNAME", user_firstname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_LASTNAME", user_lastname.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_NUMBER", user_number.toString().replace("[", "").replace("]", ""));
						request.getSession().setAttribute("USER_EMAIL", user_email.toString().replace("[", "").replace("]", ""));
						if (password.equals(painter.getUserNumber())) {
							return "redirect:/profile.jsp";
						} else {
						return "redirect:/painterUI.jsp";
						}
					} else {
						return "redirect:/login.jsp";
					}
					
			   	default:  
			    return "redirect:/login.jsp";  
		 }
	}
	@PostMapping("/update_user")
	public String updateUser(@RequestParam String user_role, @RequestParam String username,
			@RequestParam String user_email, @RequestParam String user_hostel, @RequestParam String user_block,
			@RequestParam String user_room_number, @RequestParam String password, @RequestParam int user_id) {
		
		User user = service.getUser(user_id);

		if (user_role.equals("student")) {
			
			user.setPassword(password);

			service.saveUser(user);
		} else if (user_role.equals("custodian")) {
			
			user.setPassword(password);

			service.saveUser(user);
		} else {
			
			user.setPassword(password);

			service.saveUser(user);
		}

		return "redirect:/logout";
	}
	
	//kill session
	@RequestMapping("/logout")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@PostMapping("/submitComplaint")
	public String submitComplaint(@RequestParam String complaint_category, @RequestParam String complaint_content, @RequestParam String complaint_author_id,
			@RequestParam String complaintHostel, @RequestParam String complaintBlock, @RequestParam String complaintRoomNumber){
		
		Complaint complaint =new Complaint();
		complaint.setComplaintCategory(complaint_category);
		complaint.setComplaint_content(complaint_content);
		complaint.setComplaintAuthorId(complaint_author_id);
		complaint.setComplaintHostel(complaintHostel);
		complaint.setComplaintBlock(complaintBlock);
		complaint.setComplaintRoomNumber(complaintRoomNumber);
		
		service.saveComplaint(complaint);
		return "redirect:/studentUI.jsp";
	}
	
	@PostMapping("/submitReport")
	public String submitReport(@RequestParam String report_title, @RequestParam String report_content, @RequestParam String report_author_id, @RequestParam String student_id){
		Report report = new Report();
		report.setStudent_id(student_id);
		report.setReport_title(report_title);
		report.setReport_content(report_content);
		report.setReport_author_id(report_author_id);
		
		service.saveReport(report);
		return "redirect:/reports.jsp";
	}
				
	//approve complaint
	@RequestMapping(value="hallsOfficerView.jsp/approve/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String approveComplaint(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber,  Map<String, Object> map) {
		
		Complaint complaint = service.getComplaint(complaintId);
		complaint.setComplaint_status("approved");
		complaint.setComplaint_approved_or_rejected_by(userNumber);
		service.saveComplaint(complaint);
		
		return "redirect:/hallsOfficerApprovedComplaints.jsp";
		
	}
	
	
	//reject complaint
	@RequestMapping(value="hallsOfficerView.jsp/reject/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String rejectComplaint(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map) {
		
		Complaint complaint = service.getComplaint(complaintId);
		complaint.setComplaint_status("rejected");
		complaint.setComplaint_approved_or_rejected_by(userNumber);
		service.saveComplaint(complaint);
		
		return "redirect:/hallsOfficerRejectedComplaints.jsp";
		
	}
	
	
	//Assign Complaint To Plumber Link
	@RequestMapping(value="custodianUI.jsp/plumber/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintToPlumberLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToPlumber.jsp";
		
	}
	
	//Assign Complaint To Mason Link
	@RequestMapping(value="custodianUI.jsp/mason/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintToMasonLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToMason.jsp";
		
	}
	
	//Assign Complaint To Carpenter Link
	@RequestMapping(value="custodianUI.jsp/carpenter/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintCarpenterLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToCarpenter.jsp";
		
	}
	
	
	//Assign Complaint To Security Link
	@RequestMapping(value="custodianUI.jsp/security/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintSecurityLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToSecurity.jsp";
		
	}
	
	//Assign Complaint To Electrician Link
	@RequestMapping(value="custodianUI.jsp/electrician/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintElectricianLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToElectrician.jsp";
		
	}
	
	//Assign Complaint To Cleaner Link
	@RequestMapping(value="custodianUI.jsp/cleaner/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintCleanerLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToCleaner.jsp";
		
	}
	
	//Assign Complaint To Health Link
	@RequestMapping(value="custodianUI.jsp/health/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintHealthLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToHealth.jsp";
		
	}
	
	//Assign Complaint To Painter Link
	@RequestMapping(value="custodianUI.jsp/painter/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintPainterLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToPainter.jsp";
		
	}
	
	//Assign Complaint To Custodian Link
	@RequestMapping(value="custodianUI.jsp/custodian/{complaint_id}", method=RequestMethod.GET)
		public String assignComplaintCustodianLink(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		model.addAttribute("complaintId", complaintId);
		return "/assignToCustodian.jsp";
		
	}
	
	//Assign complaint To Worker Request
	@PostMapping("/assignToWorker")
	public String assignComplaintToWorker(@RequestParam int complaint_id, @RequestParam String complaint_assigned_to, @RequestParam String complaint_assigned_by){
		
		Complaint complaint = service.getComplaint(complaint_id);
		complaint.setComplaint_assigned_to(complaint_assigned_to);
		complaint.setComplaint_assigned_by(complaint_assigned_by);
		complaint.setComplaint_status("assigned");
		service.saveComplaint(complaint);
		
		return "redirect:/assignedComplaints.jsp";
	}
	
	//Unassign Complaint
	@RequestMapping(value="assignedComplaints.jsp/unassign/{complaint_id}", method=RequestMethod.GET)
		public String unassignComplaint(@PathVariable("complaint_id") int complaintId, Map<String, Object> map, Model model) {
		
		Complaint complaint = service.getComplaint(complaintId);
		complaint.setComplaint_assigned_to("none");
		complaint.setComplaint_assigned_by("none");
		complaint.setComplaint_status("approved");
		service.saveComplaint(complaint);
		
		return "redirect:/custodianView.jsp";
		
	}
	
				
	//Done complaint by Plumber	
		@RequestMapping(value="plumberView.jsp/plumber/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintP(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
		}
		
	//Done complaint by Mason
		@RequestMapping(value="masonView.jsp/mason/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintM(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
			
		}
		
		
	//Done complaint by Carpenter
		@RequestMapping(value="carpenterView.jsp/carpenter/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintC(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
		}
		
		
	//Done complaint by Security
		@RequestMapping(value="securityView.jsp/security/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintS(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
		}
		
	//Done complaint by Electrician
		@RequestMapping(value="electricianView.jsp/electrician/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintE(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
		}
		
	//Done complaint by Cleaner
		@RequestMapping(value="cleanerView.jsp/cleaner/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintCleaner(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
			Complaint complaint = service.getComplaint(complaintId);
			model.addAttribute("complaint", complaint);
			return "/complaintDetails.jsp";
			
		}
		
	//Done complaint by Health
	@RequestMapping(value="healthView.jsp/health/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
		public String doneComplaintHealth(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
		Complaint complaint = service.getComplaint(complaintId);
		model.addAttribute("complaint", complaint);
		return "/complaintDetails.jsp";
		
	}
	
	@RequestMapping(value="complaintStatus", method=RequestMethod.POST)
	public String doneComplaint(@RequestParam int complaintId, @RequestParam String userNumber,@RequestParam String complaintStatus, @RequestParam String complaintComment ) {
	Complaint complaint = service.getComplaint(complaintId);
	complaint.setComplaint_status(complaintStatus);
	complaint.setComplaint_done_by(userNumber);
	complaint.setComplaintComment(complaintComment);
	service.saveComplaint(complaint);
	if(complaint.getComplaintCategory().equals("health")) {
	return "redirect:/healthDoneComplaints.jsp";
	}
	else if(complaint.getComplaintCategory().equals("painter")) {
		return "redirect:/painterDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("carpenter")) {
		return "redirect:/carpenterDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("mason")) {
		return "redirect:/masonDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("electrician")) {
		return "redirect:/electricianDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("cleaner")) {
		return "redirect:/cleanerDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("security")) {
		return "redirect:/securityDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("plumber")) {
		return "redirect:/plumberDoneComplaints.jsp";
		}
	else if(complaint.getComplaintCategory().equals("custodian")) {
		return "redirect:/custodianDoneComplaints.jsp";
		}
	else  {
		return "redirect:/custodian DoneComplaints.jsp";
		}
}
	
	//Done complaint by Painter
	@RequestMapping(value="painterView.jsp/painter/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
	public String doneComplaintPainter(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
		Complaint complaint = service.getComplaint(complaintId);
		model.addAttribute("complaint", complaint);
		return "/complaintDetails.jsp";
		
		
	}
	
	//Done complaint by Custodian
	@RequestMapping(value="custodianWorkspace.jsp/custodian/done/{complaint_id}/{user_number}", method=RequestMethod.GET)
	public String doneComplaintCustodian(@PathVariable("complaint_id") int complaintId, @PathVariable("user_number") String userNumber, Map<String, Object> map, Model model) {
		Complaint complaint = service.getComplaint(complaintId);
		model.addAttribute("complaint", complaint);
		return "/complaintDetails.jsp";
		
	}
		
	//Undo complaint by Plumber	
		@RequestMapping(value="plumberDoneComplaints.jsp/plumber/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintPlumber(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/plumberView.jsp";
			
		}
		
		
	//Undo complaint by Mason	
		@RequestMapping(value="masonDoneComplaints.jsp/mason/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintMason(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/masonView.jsp";
			
		}
		
		
	//Undo complaint by Carpenter	
		@RequestMapping(value="carpenterDoneComplaints.jsp/carpenter/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintCarpenter(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/carpenterView.jsp";
			
		}
		
	//Undo complaint by Security	
		@RequestMapping(value="securityDoneComplaints.jsp/security/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintSecurity(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/securityView.jsp";
			
		}
		
		
	//Undo complaint by Electrician	
		@RequestMapping(value="electricianDoneComplaints.jsp/electrician/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintElectrician(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/electricianView.jsp";
			
		}
		
		
	//Undo complaint by Cleaner	
		@RequestMapping(value="cleanerDoneComplaints.jsp/cleaner/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintCleaner(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/cleanerView.jsp";
			
		}
		
	//Undo complaint by Health
		@RequestMapping(value="healthDoneComplaints.jsp/health/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintHealth(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/healthView.jsp";
			
		}
		
	//Undo complaint by Painter
		@RequestMapping(value="painterDoneComplaints.jsp/painter/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintPainter(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/painterView.jsp";
			
		}
		
	//Undo complaint by Custodian
		@RequestMapping(value="custodianDoneComplaints.jsp/custodian/undo/{complaint_id}", method=RequestMethod.GET)
			public String undoComplaintCustodian(@PathVariable("complaint_id") int complaintId, Map<String, Object> map) {
			
			Complaint complaint = service.getComplaint(complaintId);
			complaint.setComplaint_status("assigned");
			complaint.setComplaint_done_by("none");
			service.saveComplaint(complaint);
			
			return "redirect:/custodianWorkspace.jsp";
			
		}
		
	//delete complaint by admin
	@RequestMapping(value="admin/complaints.jsp/delete/{complaintId}", method=RequestMethod.GET)
	public String deleteComplaintAdmin(@PathVariable("complaintId") int complaintId, Map<String, Object> map) {
		service.deleteComplaint(complaintId);
		return "redirect:/admin/complaints.jsp";
	
	}
	
	//delete complaint by user
	@RequestMapping(value="statusUI.jsp/delete/{complaintId}", method=RequestMethod.GET)
	public String deleteComplaintUser(@PathVariable("complaintId") int complaintId, Map<String, Object> map) {
		service.deleteComplaint(complaintId);
		return "redirect:/statusUI.jsp";
	
	}
	
	//Report Student
	@PostMapping("reportStudent" )
	public String reportUser(@RequestParam String userNumber, Model model) {
		model.addAttribute("userNumber", userNumber);
		return "reportStudentUI.jsp";
	}
	
	//delete report by Custodian
	@RequestMapping(value="reports.jsp/delete/{reportId}", method=RequestMethod.GET)
	public String deleteReportCustodian(@PathVariable("reportId") int reportId, Map<String, Object> map) {
		service.deleteReport(reportId);;
		return "redirect:/reports.jsp";
	
	}
	
	//delete report by Admin 
	@RequestMapping(value="/reports.jsp/admindelete/{reportId}", method=RequestMethod.GET)
	public String deleteReportAdmin(@PathVariable("reportId") int reportId, Map<String, Object> map) {
		service.deleteReport(reportId);;
		return "redirect:/admin/reports.jsp";
	
	}
	@GetMapping("/admin/AllComplaintsReport")
	public void generateCert( HttpServletResponse response) throws JRException, IOException {
    	JasperPrint jasper=null;
    	
    	 jasper= service.getReport();
    	 
    	 byte[] pdf = null;
    	 
    	 String filename = " complaints.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    }
	
	@RequestMapping("/admin/complaintsReportByDate")
	public void generateCert(@RequestParam String complaintStatus,@RequestParam String complaintCategory,@RequestParam String dateStart, String dateEnd, HttpServletResponse response) throws JRException, IOException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=null;
        Date endDate=null;
        try {
        	startDate = sdformat.parse(dateStart);
        	endDate = sdformat.parse(dateEnd);
                
                } 
        catch (ParseException ex) {
                    System.out.println("error parsing it bro");
                   
                }
		JasperPrint jasper=null;
		if(complaintStatus.equals("all") && complaintCategory.equals("all")) 
		{
			jasper= service.testReport(startDate, endDate);
			}
    	
		else if(complaintStatus.equals("all")) {
    	 jasper= service.testReport1(complaintCategory,startDate, endDate);
		}
		else if(complaintCategory.equals("all")) {
	    	 jasper= service.testReport(complaintStatus,startDate, endDate);
			}
		else  {
	    	 jasper= service.testReport(complaintStatus,complaintCategory,startDate, endDate);
			}
    	 byte[] pdf = null;
    	 
    	 String filename = "from"+dateStart+"To"+dateEnd+complaintStatus+complaintCategory+"complaints.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	@RequestMapping("/admin/ComplaintsReport")
	public void generateComplaintsReport(@RequestParam String complaintStatus, HttpServletResponse response) throws JRException, IOException {
		
		JasperPrint jasper=null;
    	
    	 jasper= service.testReport(complaintStatus);
    	 
    	 byte[] pdf = null;
    	 
    	 String filename =complaintStatus+ " complaints.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	
	@GetMapping("/admin/AllUsersReport")
	public void getUserReport( HttpServletResponse response) throws JRException, IOException {
    	JasperPrint jasper=null;
    	
    	 jasper= service.getuserReport();
    	 
    	 byte[] pdf = null;
    	 
    	 String filename = " users.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	
	@GetMapping("reportedStudents")
	public void getReport( HttpServletResponse response) throws JRException, IOException {
    	JasperPrint jasper=null;
    	
    	 jasper= service.getReportedStudents();
    	 
    	 byte[] pdf = null;
    	 
    	 String filename = " reportedStudents.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	@RequestMapping("/admin/usersReport")
	public void generateUsersReport(@RequestParam String userRole, HttpServletResponse response) throws JRException, IOException {
		
		JasperPrint jasper=null;
    	
    	 jasper= service.testUsersReport(userRole);
    	 
    	 byte[] pdf = null;
    	 
    	 String filename =userRole+".pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	
	@RequestMapping("/studentReport")
	public void generateStudentsReport(@RequestParam String complaintAuthor, HttpServletResponse response) throws JRException, IOException {
		
		JasperPrint jasper=null;
    	
    	 jasper= service.studentReport(complaintAuthor);
    	 
    	 byte[] pdf = null;
    	 
    	 String filename =complaintAuthor+ "complaints.pdf";
    	 
    	 pdf = JasperExportManager.exportReportToPdf(jasper);
    	 
         response.setContentType("application/pdf");
         
         response.setContentLength(pdf.length);
    	 
         response.addHeader("Content-disposition", "inline; filename=\"" + filename + "\"");
         
         OutputStream out = response.getOutputStream();
         
         JasperExportManager.exportReportToPdfStream(jasper, out);
    	 
    }
	
	@RequestMapping(value="ajaxGetHostel", method=RequestMethod.GET)
	public @ResponseBody List<Hostel> ajaxGetHostel(@RequestParam String hostel, @RequestParam String vacancy, Map<String, Object> map) {
		List <Hostel> hostels=service.getHostel(hostel, vacancy);
		return hostels;
	}
	
	@RequestMapping(value="ajaxGetAllHostels", method=RequestMethod.GET)
	public @ResponseBody List<Hostel> ajaxGetHostel(@RequestParam String vacancy, Map<String, Object> map) {
		List <Hostel> hostels=service.getHostel(vacancy);
		return hostels;
	}
	
	@RequestMapping(value="ajaxGetHostelAndBlock", method=RequestMethod.GET)
	public @ResponseBody List<Hostel> ajaxGetHostel(@RequestParam String hostel, @RequestParam String block, @RequestParam String vacancy, Map<String, Object> map) {
		List <Hostel> blocks=service.getHostel(hostel, block, vacancy);
		return blocks;
	}
	@RequestMapping(value="ajaxGetHostelAndBlockAndRoom", method=RequestMethod.GET)
	public @ResponseBody List<Hostel> ajaxGetHostel(@RequestParam String hostel, @RequestParam String block, @RequestParam String room, @RequestParam String vacancy, Map<String, Object> map) {
		List <Hostel> blocks=service.getHostel(hostel,block, room, vacancy);
		return blocks;
	}
	@PostMapping("/registerHostel")
	public String addHostel(@RequestParam String hostel, @RequestParam String block, @RequestParam String room, 
			@RequestParam String bedNo,Model model) {
		List<Hostel> hostels=service.getHostelAndBlock(hostel, block, room);
		if(hostels!=null) {
			String message="hostel, block and room already registered";
			model.addAttribute("message", message);
			return "registerHostel.jsp";
		}
		else {
			
			if(bedNo.equals("1")) {
				Hostel hostel1=new Hostel();
				hostel1.setBedNo("1");
				hostel1.setHostel(hostel);
				hostel1.setBlock(block);
				hostel1.setRoomNumber(room);
				service.saveHostel(hostel1);
			}
			if(bedNo.equals("2")) {
				Hostel hostel1=new Hostel();
				hostel1.setBedNo("1");
				hostel1.setHostel(hostel);
				hostel1.setBlock(block);
				hostel1.setRoomNumber(room);
				service.saveHostel(hostel1);
	
				Hostel hostel2=new Hostel();
				hostel2.setBedNo("2");
				hostel2.setHostel(hostel);
				hostel2.setBlock(block);
				hostel2.setRoomNumber(room);
				service.saveHostel(hostel2);			
			}
			
			if(bedNo.equals("4")) {
				Hostel hostel1=new Hostel();
				hostel1.setBedNo("1");
				hostel1.setHostel(hostel);
				hostel1.setBlock(block);
				hostel1.setRoomNumber(room);
				service.saveHostel(hostel1);
				
				Hostel hostel2=new Hostel();
				hostel2.setBedNo("2");
				hostel2.setHostel(hostel);
				hostel2.setBlock(block);
				hostel2.setRoomNumber(room);
				service.saveHostel(hostel2);	
				
				Hostel hostel3=new Hostel();
				hostel3.setBedNo("3");
				hostel3.setHostel(hostel);
				hostel3.setBlock(block);
				hostel3.setRoomNumber(room);
				service.saveHostel(hostel3);	
				
				Hostel hostel4=new Hostel();
				hostel4.setBedNo("3");
				hostel4.setHostel(hostel);
				hostel4.setBlock(block);
				hostel4.setRoomNumber(room);
				service.saveHostel(hostel4);	
			}
			
			if(bedNo.equals("6")) {
				Hostel hostel1=new Hostel();
				hostel1.setBedNo("1");
				hostel1.setHostel(hostel);
				hostel1.setBlock(block);
				hostel1.setRoomNumber(room);
				service.saveHostel(hostel1);
				
				Hostel hostel2=new Hostel();
				hostel2.setBedNo("2");
				hostel2.setHostel(hostel);
				hostel2.setBlock(block);
				hostel2.setRoomNumber(room);
				service.saveHostel(hostel2);	
				
				Hostel hostel3=new Hostel();
				hostel3.setBedNo("3");
				hostel3.setHostel(hostel);
				hostel3.setBlock(block);
				hostel3.setRoomNumber(room);
				service.saveHostel(hostel3);	
				
				Hostel hostel4=new Hostel();
				hostel4.setBedNo("3");
				hostel4.setHostel(hostel);
				hostel4.setBlock(block);
				hostel4.setRoomNumber(room);
				service.saveHostel(hostel4);
				
				Hostel hostel5=new Hostel();
				hostel5.setBedNo("3");
				hostel5.setHostel(hostel);
				hostel5.setBlock(block);
				hostel5.setRoomNumber(room);
				service.saveHostel(hostel5);	
				
				Hostel hostel6=new Hostel();
				hostel6.setBedNo("3");
				hostel6.setHostel(hostel);
				hostel6.setBlock(block);
				hostel6.setRoomNumber(room);
				service.saveHostel(hostel6);
			}
			
			
			String message="Successfully registered!";
			model.addAttribute("message", message);
			return "registerHostel.jsp";
		}
		
	}
	
}
