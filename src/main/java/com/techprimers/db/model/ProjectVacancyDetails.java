package com.techprimers.db.model;

import java.util.List;

public class ProjectVacancyDetails {
	Integer project_id;
	Integer job_status_id;
	String est_start_date;
	String est_end_date;
	Integer project_vacancy_amt;
	Integer skill_id;
	Integer years_experience;
	
	public Integer getProject_id() {
		return project_id;
	}
	
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	
	public Integer getJob_status_id() {
		return job_status_id;
	}
	
	public void setJob_status_id(Integer job_status_id) {
		this.job_status_id = job_status_id;
	}
	
	public String getEst_start_date() {
		return est_start_date;
	}
	
	public void setEst_start_date(String est_start_date) {
		this.est_start_date = est_start_date;
	}
	
	public String getEst_end_date() {
		return est_end_date;
	}
	
	public void setEst_end_date(String est_end_date) {
		this.est_end_date = est_end_date;
	}
	
	public Integer getProject_vacancy_amt() {
		return project_vacancy_amt;
	}
	
	public void setProject_vacancy_amt(Integer project_vacancy_amt) {
		this.project_vacancy_amt = project_vacancy_amt;
	}
	
	public Integer getSkill_id() {
		return skill_id;
	}
	
	public void setSkill_id(Integer skill_id) {
		this.skill_id = skill_id;
	}
	
	public Integer getYears_experience() {
		return years_experience;
	}
	
	public void setYears_experience(Integer years_experience) {
		this.years_experience = years_experience;
	}
	
}
