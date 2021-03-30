package com.demo.Accommodation_Complaints_Feedback_System.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.demo.Accommodation_Complaints_Feedback_System.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.demo.Accommodation_Complaints_Feedback_System.model.Complaint;
import com.demo.Accommodation_Complaints_Feedback_System.model.Report;
import com.demo.Accommodation_Complaints_Feedback_System.repository.ComplaintRepository;
import com.demo.Accommodation_Complaints_Feedback_System.repository.ReportRepository;
import com.demo.Accommodation_Complaints_Feedback_System.model.User;


@Service
public class ServiceHalls {
	
	@Autowired
	UserRepository user_repository;
	
	@Autowired
	ComplaintRepository complaint_repository;
	
	@Autowired
	ReportRepository report_repository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void saveReport(Report report) {
		report_repository.save(report);
	}
	
	public void saveUser(User user) {
	 	user_repository.save(user);
	}
	
	public User getUser(String username, String password) {
		return user_repository.findByUsernameAndPassword(username, password);
	}
	
	public User getUser(int user_id) {
		return user_repository.findById(user_id);
	}
	
	
	public void deleteUser(int user_id) {
		user_repository.deleteById(user_id);
	}
	
	public void saveComplaint(Complaint complaint) {
		complaint_repository.save(complaint);	
	}
	
	
	public Complaint getComplaint(int complaint_id) {
		return complaint_repository.findById(complaint_id);
	}
	
	public void deleteComplaint(int cid) {
		complaint_repository.deleteById(cid);
	}
	
	public void deleteReport(int report_id) {
		report_repository.deleteById(report_id);
	}
	public JasperPrint getReport() throws FileNotFoundException, JRException {
		List<Complaint> complaints = complaint_repository.findAll();
		
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}
	
}
