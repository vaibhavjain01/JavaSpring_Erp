package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectBudget {
	@Id
    @GeneratedValue
    @Column(name = "project_budget_id")
    private Integer projectBudgetId;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "project_budget_amt")
    private Integer projectBudgetAmt;
	public Integer getProjectBudgetId() {
		return projectBudgetId;
	}
	public void setProjectBudgetId(Integer projectBudgetId) {
		this.projectBudgetId = projectBudgetId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getProjectBudgetAmt() {
		return projectBudgetAmt;
	}
	public void setProjectBudgetAmt(Integer projectBudgetAmt) {
		this.projectBudgetAmt = projectBudgetAmt;
	}
}
