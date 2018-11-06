package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectEmployees;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectEmployeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectemployees")
public class ProjectEmployeesResource {
	private static ProjectEmployeesRepository projectEmployeesRepository;
	
	public static boolean checkIfEmpAssignedToProj(Integer empId, Integer projId) {
		if(projectEmployeesRepository == null) {
			return false;
		}
		if(projectEmployeesRepository.findByProjectIdAndEmployeeId(projId, empId) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean assignEmpToProj(Integer empId, Integer projId) {
		if(checkIfEmpAssignedToProj(empId, projId) == true) {
			return false;
		}
		ProjectEmployees projEmp = new ProjectEmployees();
		projEmp.setEmployeeId(empId);
		projEmp.setProjectId(projId);
		projectEmployeesRepository.save(projEmp);
		return true;
	}
	
	public static boolean deleteEmpFromProj(Integer empId, Integer projId) {
		if(checkIfEmpAssignedToProj(empId, projId) == false) {
			return false;
		}
		projectEmployeesRepository.deleteByProjectIdAndEmployeeId(projId, empId);
		return true;
	}
}
