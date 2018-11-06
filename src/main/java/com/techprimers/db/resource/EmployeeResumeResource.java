package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Employee;
import com.techprimers.db.model.EmployeeResume;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.EmployeeResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/employeeresume")
public class EmployeeResumeResource {
	@Autowired
	private static EmployeeResumeRepository employeeResumeRepository;
	
	public static void setRepo(EmployeeResumeRepository inEmployeeResumeRepository) {
		employeeResumeRepository = inEmployeeResumeRepository;
    }
	
	public static boolean checkIfEmployeeExist(Integer empId) {
		EmployeeResume res = employeeResumeRepository.findByEmployeeId(empId);
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addEmployeeResume(Integer empId, String resumeText, String resumeDate) {
		EmployeeResume res = employeeResumeRepository.findByEmployeeId(empId);
		if(res != null) {
			employeeResumeRepository.deleteByEmployeeId(empId);
		}
		
		EmployeeResume empRes = new EmployeeResume();
		empRes.setEmployeeId(empId);
		empRes.setResumeDate(resumeDate);
		empRes.setResumeText(resumeText);
		employeeResumeRepository.save(empRes);
		res = employeeResumeRepository.findByEmployeeId(empId);
		return true;
	}
	
	public static boolean addNewEmployeeResume(Integer empId, String resumeText, String resumeDate) {
		return addEmployeeResume(empId, resumeText, resumeDate);		
	}
	
	public static boolean modifyEmployeeResume(Integer resumeId, Integer employeeId, 
			String resumeDate, String resumeText) {
		EmployeeResume empRes = new EmployeeResume();
		empRes.setResumeId(resumeId);
		empRes.setEmployeeId(employeeId);
		empRes.setResumeDate(resumeDate);
		empRes.setResumeText(resumeText);
		employeeResumeRepository.save(empRes);
		return true;
	}
	
	public static boolean deleteEmployeeResume(Integer empId) {
		if(employeeResumeRepository == null) {
			return false;
		}
		
		Integer empResId = employeeResumeRepository.findByEmployeeId(empId).getResumeId();
		employeeResumeRepository.delete(empResId);
		return true;
	}
}
