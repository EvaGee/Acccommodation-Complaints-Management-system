package com.demo.Accommodation_Complaints_Feedback_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Accommodation_Complaints_Feedback_System.model.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
	public User findByUsernameAndPassword(String username, String password);
	public User findById(int user_id);
	public List<User> findAllByUserRole(String userRole);
	public User findByUserNumber(String user_number);
}
