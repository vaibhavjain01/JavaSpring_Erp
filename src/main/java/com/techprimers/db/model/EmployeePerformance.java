package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeePerformance {
	@Id
    @GeneratedValue
    @Column(name = "employee_performance_id")
    private Integer employeePerformanceId;
	@Column(name = "employee_id")
    private Integer employeeId;
	@Column(name = "rating_year")
    private Integer ratingYear;
	@Column(name = "rating_scale_ten")
    private Integer ratingScaleTen;
	public Integer getEmployeePerformanceId() {
		return employeePerformanceId;
	}
	public void setEmployeePerformanceId(Integer employeePerformanceId) {
		this.employeePerformanceId = employeePerformanceId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
