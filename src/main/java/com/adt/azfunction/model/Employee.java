package com.adt.azfunction.model;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
	private Long employeeId;
	private Date createdAt;
	private Date updatedAt;
	private Boolean isActive;
	private String email;
	private String firstName;
	private Boolean isEmailVerified;
	private String lastName;
	private String password;
	private String username;
	private Date dob;
	private String gender;
	private String maritalStatus;
	private String mobileNo;
	private String middleName;
	private String confirmPassword;
	private Long adtId;
	private String employeeType;
	private Date updatedWhen;
	private Boolean isWeekendWorking;
}
