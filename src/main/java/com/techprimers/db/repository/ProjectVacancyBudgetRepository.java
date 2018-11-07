package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectVacancyBudget;

public interface ProjectVacancyBudgetRepository extends JpaRepository<ProjectVacancyBudget, Integer> {
	ProjectVacancyBudget findByProjectVacancyId(Integer projectVacancyId);
	Integer deleteByProjectVacancyId(Integer projectVacancyId);
	
}
