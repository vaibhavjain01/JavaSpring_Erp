package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeeSkills {
	@Id
	@GeneratedValue
	@Column(name = "employee_skill_id")
	private Integer empSkillId;
    @Column(name = "skill_id")
    private Integer skillId;
	@Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "years_experience")
    private Integer yearsExperience;
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getYearsExperience() {
		return yearsExperience;
	}
	public void setYearsExperience(Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
	public Integer getEmpSkillId() {
		return empSkillId;
	}
	public void setEmpSkillId(Integer empSkillId) {
		this.empSkillId = empSkillId;
	}
}
