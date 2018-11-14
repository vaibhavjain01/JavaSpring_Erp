package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectVacancy {
	@Id
    @GeneratedValue
    @Column(name = "project_vacancy_id")
    private Integer projectVacancyId;
	@Column(name = "project_id")
    private Integer projectId;
	@Column(name = "job_status_id")
    private Integer jobStatusId;
    @Column(name = "est_start_date")
    private String estStartDate;
    @Column(name = "est_end_date")
    private String estEndDate;
    
	public Integer getProjectVacancyId() {
		return projectVacancyId;
	}
	public void setProjectVacancyId(Integer projectVacancyId) {
		this.projectVacancyId = projectVacancyId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getJobStatusId() {
		return jobStatusId;
	}
	public void setJobStatusId(Integer jobStatusId) {
		this.jobStatusId = jobStatusId;
	}
	public String getEstStartDate() {
		return estStartDate;
	}
	public void setEstStartDate(String estStartDate) {
		this.estStartDate = estStartDate;
	}
	public String getEstEndDate() {
		return estEndDate;
	}
	public void setEstEndDate(String estEndDate) {
		this.estEndDate = estEndDate;
	}
}
