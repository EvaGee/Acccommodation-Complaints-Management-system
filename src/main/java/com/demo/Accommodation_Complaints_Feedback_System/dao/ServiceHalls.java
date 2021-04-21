package com.demo.Accommodation_Complaints_Feedback_System.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
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
import com.demo.Accommodation_Complaints_Feedback_System.model.Hostel;
import com.demo.Accommodation_Complaints_Feedback_System.model.Report;
import com.demo.Accommodation_Complaints_Feedback_System.repository.ComplaintRepository;
import com.demo.Accommodation_Complaints_Feedback_System.repository.HostelRepository;
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
	HostelRepository hostelRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void saveReport(Report report) {
		report_repository.save(report);
	}
	
	public void saveUser(User user) {
		 
	 	user_repository.save(user);	
	}
	
	public void saveHostel(Hostel hostel) {
		 
	 	hostelRepository.save(hostel);	
	}
	
	public User getUser(String user_number) {
		return user_repository.findByUserNumber(user_number);
	}
	
	public User getUser(String username, String password) {
		return user_repository.findByUsernameAndPassword(username, password);
	}
	
	public User getUser(int user_id) {
		return user_repository.findById(user_id);
	}
	
	public List<Hostel> getHostel(String hostel, String vacancy) {
		return hostelRepository.findByHostelAndVacancy(hostel, vacancy);
	}
	
	public List<Hostel> getHostel(String vacancy) {
		return hostelRepository.findByVacancy(vacancy);
	}
	
	public List<Hostel> getHostel(String hostel,String block, String vacancy) {
		return hostelRepository.findByHostelAndBlockAndVacancy(hostel,block, vacancy);
	}
	
	public List<Hostel> getHostel(String hostel,String block, String room, String vacancy) {
		return hostelRepository.findByHostelAndBlockAndRoomNumberAndVacancy(hostel,block,room,vacancy);
	}
	
	public Hostel getHostelAndBedNo(String hostel,String block, String room, String bedNo) {
		return hostelRepository.findByHostelAndBlockAndRoomNumberAndBedNo(hostel,block,room,bedNo);
	}
	
	public List<Hostel> getHostelAndBlock(String hostel,String block, String room) {
		return hostelRepository.findByHostelAndBlockAndRoomNumber(hostel,block,room);
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
	
	public JasperPrint testReport(String complaintStatus,Date startDate, Date endDate) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findByComplaintStatusAndCreatedAtBetween(complaintStatus, startDate, endDate);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	} 
	public JasperPrint testReport1(String complaintCategory,Date startDate, Date endDate) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findByComplaintCategoryAndCreatedAtBetween(complaintCategory, startDate, endDate);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	} 
	public JasperPrint testReport(String complaintStatus,String complaintCategory,Date startDate, Date endDate) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findByComplaintStatusAndComplaintCategoryAndCreatedAtBetween(complaintStatus,complaintCategory, startDate, endDate);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	} 
	public JasperPrint testReport(Date startDate, Date endDate) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findByCreatedAtBetween(startDate, endDate);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	} 
	public JasperPrint testReport(String complaintStatus) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findAllByComplaintStatus(complaintStatus);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	public JasperPrint getuserReport() throws FileNotFoundException, JRException {
		List<User> users = user_repository.findAll();
		
		File file = ResourceUtils.getFile("classpath:users.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	public JasperPrint getReportedStudents() throws FileNotFoundException, JRException {
		List<Report> reports = report_repository.findAll();
		
		File file = ResourceUtils.getFile("classpath:reports.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	
	public JasperPrint testUsersReport(String userRole) throws FileNotFoundException, JRException {
		List<User> users = user_repository.findAllByUserRole(userRole);

		if(userRole.equals("student")) {
		File file = ResourceUtils.getFile("classpath:users.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		}
		else if(userRole.equals("custodian")){
			File file = ResourceUtils.getFile("classpath:users2.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "Admin");

	    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		}
		else {
			File file = ResourceUtils.getFile("classpath:users1.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "Admin");

	    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		}

		
	} 
	public JasperPrint studentReport(String complaintAuthor) throws JRException, IOException {
		List<Complaint> complaints = complaint_repository.findAllByComplaintAuthorId(complaintAuthor);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:complaints.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(complaints);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Admin");

    	return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}

	
	
}
