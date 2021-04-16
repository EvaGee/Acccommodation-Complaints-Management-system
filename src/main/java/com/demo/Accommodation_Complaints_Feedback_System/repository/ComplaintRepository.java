package com.demo.Accommodation_Complaints_Feedback_System.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Accommodation_Complaints_Feedback_System.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	public Complaint findById(int complaint_id);
	
	public List<Complaint> findByComplaintStatusAndCreatedAtBetween(String complaintStatus, Date startDate, Date endDate);
	
	public List<Complaint> findByComplaintCategoryAndCreatedAtBetween(String complaintCategory, Date startDate, Date endDate);
	
	public List<Complaint> findByComplaintStatusAndComplaintCategoryAndCreatedAtBetween(String complaintStatus,String complaintCategory, Date startDate, Date endDate);
	
	public List<Complaint> findAllByComplaintStatus(String complaintStatus);
	
	public List<Complaint> findByCreatedAtBetween(Date startDate, Date endDate);
	
	public List<Complaint> findAllByComplaintAuthorId(String complaintAuthor);

	public List<Complaint> findByComplaintHostel(String hostel);
	
	public List<Complaint> findByComplaintHostelAndComplaintBlock(String hostel, String block);
	
	
}
