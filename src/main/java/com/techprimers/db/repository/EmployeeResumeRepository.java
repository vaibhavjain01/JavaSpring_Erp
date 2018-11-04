package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.EmployeeResume;

public interface EmployeeResumeRepository extends JpaRepository<EmployeeResume, Integer> {
	EmployeeResume findByEmployeeId(Integer employeeId);
	void deleteByEmployeeId(Integer employeeId);
}
