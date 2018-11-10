package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByUsername(String username);
	Employee findByEmployeeId(Integer employeeId);
	Integer deleteByUsername(String username);
}
