package com.techprimers.db.model;

import java.util.List;

import javax.persistence.Column;

public class EmployeeDetails {
	private String username;
    private String password;
    private String address;
    private String jobStatusText;
    private String name;
    private String dob;
    private String email;
    private Integer salaryPerHour;
    private String resumeDate;
    private String resumeText;
    
    private List<Skills> skillName;
    private List<EmployeeSkills> yearsExperience;
    
    private Integer ratingYear;
    private Integer ratingScaleTen;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJobStatusText() {
		return jobStatusText;
	}
	public void setJobStatusText(String jobStatusText) {
		this.jobStatusText = jobStatusText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getSalaryPerHour() {
		return salaryPerHour;
	}
	public void setSalaryPerHour(Integer salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}
	public String getResumeDate() {
		return resumeDate;
	}
	public void setResumeDate(String resumeDate) {
		this.resumeDate = resumeDate;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public List<Skills> getSkillName() {
		return skillName;
	}
	public void setSkillName(List<Skills> skillName) {
		this.skillName = skillName;
	}
	public List<EmployeeSkills> getYearsExperience() {
		return yearsExperience;
	}
	public void setYearsExperience(List<EmployeeSkills> yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
	public Integer getRatingYear() {
		return ratingYear;
	}
	public void setRatingYear(Integer ratingYear) {
		this.ratingYear = ratingYear;
	}
	public Integer getRatingScaleTen() {
		return ratingScaleTen;
	}
	public void setRatingScaleTen(Integer ratingScaleTen) {
		this.ratingScaleTen = ratingScaleTen;
	}
}
