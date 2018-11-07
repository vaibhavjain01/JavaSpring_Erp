package com.techprimers.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.techprimers.db.model.EmployeePerformance;
import com.techprimers.db.model.EmployeeSkills;

public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Integer> {
	List<EmployeeSkills> findByEmployeeId(Integer employeeId);
	Integer deleteByEmployeeIdAndSkillId(@Param("employee_id") Integer employeeId, @Param("skill_id") Integer skillId);
}
