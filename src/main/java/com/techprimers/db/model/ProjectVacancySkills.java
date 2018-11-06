package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectVacancySkills {
	@Id
    @GeneratedValue
    @Column(name = "project_vacancy_id")
    private Integer projectVacancyId;
	@Column(name = "skill_id")
    private Integer skillId;
	@Column(name = "years_experience")
    private Integer yearsExperience;
	
	public Integer getProjectVacancyId() {
		return projectVacancyId;
	}
	public void setProjectVacancyId(Integer projectVacancyId) {
		this.projectVacancyId = projectVacancyId;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Integer getYearsExperience() {
		return yearsExperience;
	}
	public void setYearsExperience(Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
}
