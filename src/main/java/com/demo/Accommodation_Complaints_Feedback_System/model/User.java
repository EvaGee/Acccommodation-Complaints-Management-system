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
@Table(name="Users")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	private int user_id;
	@NotBlank

	private String userNumber;
	
	@NotBlank
	private String user_firstname;
	
	@NotBlank
	private String user_lastname;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String user_email;
	
	@NotBlank
	private String userRole;
	
	private String user_hostel;
	
	private String user_block;
	
	private String user_room_number;
	
	@CreatedDate
	private Date createdAt;
	
	@NotBlank
	private String password;
	
	
	public String getUser_hostel() {
		return user_hostel;
	}

	public void setUser_hostel(String user_hostel) {
		this.user_hostel = user_hostel;
	}

	public String getUser_block() {
		return user_block;
	}

	public void setUser_block(String user_block) {
		this.user_block = user_block;
	}

	public String getUser_room_number() {
		return user_room_number;
	}

	public void setUser_room_number(String user_room_number) {
		this.user_room_number = user_room_number;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUser_firstname() {
		return user_firstname;
	}

	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}

	public String getUser_lastname() {
		return user_lastname;
	}

	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_number=" + userNumber + ", user_firstname=" + user_firstname
				+ ", user_lastname=" + user_lastname + ", username=" + username + ", user_email=" + user_email
				+ ", user_role=" + userRole + ", user_hostel=" + user_hostel + ", user_block=" + user_block
				+ ", user_room_number=" + user_room_number + ", password=" + password + "]";
	}

	
}
