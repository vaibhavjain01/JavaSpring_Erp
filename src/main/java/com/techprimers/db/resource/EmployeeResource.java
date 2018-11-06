package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Employee;
import com.techprimers.db.model.JobStatus;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.Column;

@RestController
@RequestMapping(value = "/rest/employee")
public class EmployeeResource {
	@Autowired
	private static EmployeeRepository employeeRepository;
	
	public static void setRepo(EmployeeRepository inEmployeeRepository) {
		employeeRepository = inEmployeeRepository;
    }
	
	public static boolean checkIfEmployeeExist(String username) {
		Employee res = employeeRepository.findByUsername(username);
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addEmployee(String name, Integer jobStatusId, String dob, 
			String email, String username, Integer addressId, Integer salaryPerHour) {
		Employee res = employeeRepository.findByUsername(username);
		if(res == null) {
			Employee emp = new Employee();
			emp.setName(name);
			emp.setUsername(username);
			emp.setJobStatusId(jobStatusId);
			emp.setDob(dob);
			emp.setEmail(email);
			emp.setAddressId(addressId);
			emp.setSalaryPerHour(salaryPerHour);
			employeeRepository.save(emp);
			res = employeeRepository.findByUsername(username);
			return true;
		}
		return false;
	}
	
	public static boolean addNewEmployee(String name, Integer jobStatusId, String dob, 
			String email, String username, Integer addressId, Integer salaryPerHour) {
		return addEmployee(name, jobStatusId, dob, email, username, 
				addressId, salaryPerHour);		
	}
	
	public static Integer getEmployeeId(String username) {
		Employee emp = employeeRepository.findByUsername(username);
		if(emp == null) {
			return null;
		}
		return emp.getEmployeeId();
	}
	
	public static boolean deleteEmployee(String userName) {
		if(employeeRepository == null) {
			return false;
		}
		employeeRepository.deleteByUsername(userName);
		return true;
	}
}
