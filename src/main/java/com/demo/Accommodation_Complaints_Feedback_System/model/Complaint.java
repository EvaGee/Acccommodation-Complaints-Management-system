package com.demo.Accommodation_Complaints_Feedback_System.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Complaints")
@EntityListeners(AuditingEntityListener.class)
public class Complaint {
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int complaint_id;
	
	@NotBlank 
	private String complaintCategory;
	
	
	private String complaintComment;
	
	

	@NotBlank
	private String complaint_content;
	
	@NotBlank
	private String complaintStatus = "pending";


	@NotBlank
	private String complaintHostel;
	
	@NotBlank
	private String complaintBlock;
	
	private String complaintRoomNumber;
	
	@NotBlank
	private String complaint_approved_or_rejected_by="none";
	@NotBlank
	private String complaintAuthorId;
	@NotBlank
	private String complaint_assigned_to="none";
	@NotBlank
	private String complaint_assigned_by="none";
	@NotBlank
	private String complaint_done_by="none";
	
	@CreatedDate
	private Date createdAt;

	
	public String getComplaint_assigned_by() {
		return complaint_assigned_by;
	}

	public void setComplaint_assigned_by(String complaint_assigned_by) {
		this.complaint_assigned_by = complaint_assigned_by;
	}
	public String getComplaintRoomNumber() {
		return complaintRoomNumber;
	}

	public void setComplaintRoomNumber(String complaintRoomNumber) {
		this.complaintRoomNumber = complaintRoomNumber;
	}

	public String getComplaint_assigned_to() {
		return complaint_assigned_to;
	}

	public void setComplaint_assigned_to(String complaint_assigned_to) {
		this.complaint_assigned_to = complaint_assigned_to;
	}

	public String getComplaint_approved_or_rejected_by() {
		return complaint_approved_or_rejected_by;
	}

	public void setComplaint_approved_or_rejected_by(String complaint_approved_or_rejected_by) {
		this.complaint_approved_or_rejected_by = complaint_approved_or_rejected_by;
	}

	public String getComplaint_done_by() {
		return complaint_done_by;
	}

	public void setComplaint_done_by(String complaint_done_by) {
		this.complaint_done_by = complaint_done_by;
	}

	public String getComplaintCategory() {
		return complaintCategory;
	}

	public void setComplaintCategory(String complaintCategory) {
		this.complaintCategory = complaintCategory;
	}

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}

	public String getComplaint_content() {
		return complaint_content;
	}

	public void setComplaint_content(String complaint_content) {
		this.complaint_content = complaint_content;
	}

	public String getComplaint_status() {
		return complaintStatus;
	}

	public void setComplaint_status(String complaint_status) {
		this.complaintStatus = complaint_status;
	}

	public String getComplaintAuthorId() {
		return complaintAuthorId;
	}

	public void setComplaintAuthorId(String complaint_author_id) {
		this.complaintAuthorId = complaint_author_id;
	}
	

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public String getComplaintHostel() {
		return complaintHostel;
	}

	public void setComplaintHostel(String complaintHostel) {
		this.complaintHostel = complaintHostel;
	}


	public String getComplaintBlock() {
		return complaintBlock;
	}

	public void setComplaintBlock(String complaintBlock) {
		this.complaintBlock = complaintBlock;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getComplaintComment() {
		return complaintComment;
	}

	public void setComplaintComment(String complaintComment) {
		this.complaintComment = complaintComment;
	}
	
	@Override
	public String toString() {
		return "Complaint [complaint_id=" + complaint_id + ", complaint_category=" + complaintCategory
				+ ", complaint_content=" + complaint_content + ", complaint_status=" + complaintStatus
				+ ", complaint_approved_or_rejected_by=" + complaint_approved_or_rejected_by + ", complaint_author_id="
				+ complaintAuthorId + ", complaint_assigned_to=" + complaint_assigned_to + ", complaint_assigned_by="
				+ complaint_assigned_by + ", complaint_done_by=" + complaint_done_by+","
						+ "complaintBlock=" + complaintBlock+  ", complaintRoomNumber=" + complaintRoomNumber+"]";
	}


}
