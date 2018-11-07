package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectBudget;

public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, Integer> {
	ProjectBudget findByProjectId(Integer projectId);
	Integer deleteByProjectId(Integer projectId);
}
