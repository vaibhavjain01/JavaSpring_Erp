package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.EmployeePerformance;
import com.techprimers.db.model.EmployeeResume;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/employeeperformance")
public class EmployeePerformanceResource {
	@Autowired
	private static EmployeePerformanceRepository employeePerformanceRepository;
	
	public static void setRepo(EmployeePerformanceRepository inemployeePerformanceRepository) {
		employeePerformanceRepository = inemployeePerformanceRepository;
    }
	
	public static boolean checkIfEmployeePerformanceExist(Integer empId) {
		EmployeePerformance res = employeePerformanceRepository.findByEmployeeId(empId);
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addEmployeePerformance(Integer empId, Integer ratingYear, 
			Integer ratingScaleTen) {
		EmployeePerformance res = employeePerformanceRepository.findByEmployeeId(empId);
		if(res != null) {
			employeePerformanceRepository.deleteByEmployeeId(empId);
		}
		
		EmployeePerformance empPerf = new EmployeePerformance();
		empPerf.setEmployeeId(empId);
		empPerf.setRatingScaleTen(ratingScaleTen);
		empPerf.setRatingYear(ratingYear);
		employeePerformanceRepository.save(empPerf);
		res = employeePerformanceRepository.findByEmployeeId(empId);
		return true;
	}
	
	public static boolean addNewEmployeePerformance(Integer empId) {
		return addEmployeePerformance(empId, 0, 0);		
	}
	
	public static boolean deleteEmployeePerformance(Integer empId) {
		if(employeePerformanceRepository == null) {
			return false;
		}
		Integer empPerfId = employeePerformanceRepository.findByEmployeeId(empId).getEmployeePerformanceId();
		employeePerformanceRepository.delete(empPerfId);
		return true;
	}
	
	public static boolean modifyEmployeePerformance(Integer empPerfId, Integer empId,
			Integer ratingYear, Integer ratingScaleTen) {
		if(employeePerformanceRepository == null) {
			return false;
		}
		EmployeePerformance empPerf = new EmployeePerformance();
		empPerf.setEmployeePerformanceId(empPerfId);
		empPerf.setEmployeeId(empId);
		empPerf.setRatingScaleTen(ratingScaleTen);
		empPerf.setRatingYear(ratingYear);
		employeePerformanceRepository.save(empPerf);
		return true;
	}
}
