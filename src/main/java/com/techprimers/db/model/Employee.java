package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "name")
    private String name;
    @Column(name = "job_status_id")
    private Integer jobStatusId;
    @Column(name = "dob")
    private String dob;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "salary_per_hour")
    private Integer salaryPerHour;
    
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getJobStatusId() {
		return jobStatusId;
	}
	public void setJobStatusId(Integer jobStatusId) {
		this.jobStatusId = jobStatusId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getSalaryPerHour() {
		return salaryPerHour;
	}
	public void setSalaryPerHour(Integer salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}
}
