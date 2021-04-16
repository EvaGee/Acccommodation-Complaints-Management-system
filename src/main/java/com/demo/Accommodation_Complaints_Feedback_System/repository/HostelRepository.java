package com.demo.Accommodation_Complaints_Feedback_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Accommodation_Complaints_Feedback_System.model.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Integer>{
	public List<Hostel> findByHostelAndVacancy(String hostel, String vacancy);
	
	public List<Hostel> findByHostelAndBlockAndVacancy(String hostel, String block, String vacancy );
	
	public List<Hostel> findByHostelAndBlockAndRoomNumberAndVacancy(String hostel, String block, String room, String vacancy);
	
	public Hostel findByHostelAndBlockAndRoomNumberAndBedNo(String hostel, String block, String room, String bedNo);
}
