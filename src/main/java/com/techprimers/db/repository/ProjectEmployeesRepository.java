package com.techprimers.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectEmployees;

public interface ProjectEmployeesRepository extends JpaRepository<ProjectEmployees, Integer> {
	List<ProjectEmployees> findByProjectId(Integer projectId);
	List<ProjectEmployees> findByEmployeeId(Integer employeeId);
	ProjectEmployees findByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
	boolean deleteByProjectId(Integer projectId);
	boolean deleteByEmployeeId(Integer employeeId);
	boolean deleteByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
}
