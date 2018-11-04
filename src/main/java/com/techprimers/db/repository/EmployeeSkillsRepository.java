package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.EmployeePerformance;
import com.techprimers.db.model.EmployeeSkills;

public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Integer> {
	EmployeeSkills findByEmployeeId(Integer employeeId);
	void deleteByEmployeeId(Integer employeeId);
}
