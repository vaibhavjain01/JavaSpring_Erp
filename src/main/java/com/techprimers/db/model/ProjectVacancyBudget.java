package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectVacancyBudget {
	@Id
    @GeneratedValue
    @Column(name = "project_vacancy_budget_id")
    private Integer projectVacancyBudgetId;
	@Column(name = "project_vacancy_id")
    private Integer projectVacancyId;
	@Column(name = "project_vacancy_budget_amt")
    private Integer projectVacancyBudgetAmt;
	public Integer getProjectVacancyBudgetId() {
		return projectVacancyBudgetId;
	}
	public void setProjectVacancyBudgetId(Integer projectVacancyBudgetId) {
		this.projectVacancyBudgetId = projectVacancyBudgetId;
	}
	public Integer getProjectVacancyId() {
		return projectVacancyId;
	}
	public void setProjectVacancyId(Integer projectVacancyId) {
		this.projectVacancyId = projectVacancyId;
	}
	public Integer getProjectVacancyBudgetAmt() {
		return projectVacancyBudgetAmt;
	}
	public void setProjectVacancyBudgetAmt(Integer projectVacancyBudgetAmt) {
		this.projectVacancyBudgetAmt = projectVacancyBudgetAmt;
	}
}
