package com.techprimers.db.model;

import java.util.List;

public class ProjectDetails {
	String projectName;
	String startDate;
	String endDate;
	Integer projectBudget;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getProjectBudget() {
		return projectBudget;
	}
	public void setProjectBudget(Integer projectBudget) {
		this.projectBudget = projectBudget;
	}
}
