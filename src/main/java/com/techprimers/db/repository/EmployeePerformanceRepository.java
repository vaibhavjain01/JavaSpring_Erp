package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.EmployeePerformance;

public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Integer> {
	EmployeePerformance findByEmployeeId(Integer employeeId);
	void deleteByEmployeeId(Integer employeeId);
}
