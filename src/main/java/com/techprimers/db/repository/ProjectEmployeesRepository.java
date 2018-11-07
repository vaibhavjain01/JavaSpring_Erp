package com.techprimers.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectEmployees;

public interface ProjectEmployeesRepository extends JpaRepository<ProjectEmployees, Integer> {
	List<ProjectEmployees> findByProjectId(Integer projectId);
	List<ProjectEmployees> findByEmployeeId(Integer employeeId);
	ProjectEmployees findByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
	Integer deleteByProjectId(Integer projectId);
	Integer deleteByEmployeeId(Integer employeeId);
	Integer deleteByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
}
