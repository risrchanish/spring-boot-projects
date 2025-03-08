package com.risrch.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long Id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	
	public Student() {
		
	}

	public Student(Long id, String firstName, String lastName, String email) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
