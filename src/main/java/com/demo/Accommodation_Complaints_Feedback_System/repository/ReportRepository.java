package com.demo.Accommodation_Complaints_Feedback_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Accommodation_Complaints_Feedback_System.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	public Report findById(int report_id);
}
