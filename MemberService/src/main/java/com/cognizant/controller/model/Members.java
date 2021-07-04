package com.cognizant.controller.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Members {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memberId;
	
	@Column(name="first_Name")
	private String firstName;
	
	@Column(name = "last_Name")
	private String lastName;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;
	
	//@Column(name = "emailId")
	//private String emailId;
	
	//@Column(name = "password")
	//private String password;
	
	@Column(name = "policy_Code")
	private String policyId;
	
	@Column(name = "address")
	private String address;
	
	/*@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zip_Code")
	private String zipCode; */

}
