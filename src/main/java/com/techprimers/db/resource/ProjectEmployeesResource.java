package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectDetails;
import com.techprimers.db.model.ProjectEmployees;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectEmployeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectemployees")
public class ProjectEmployeesResource {
	@Autowired
	private static ProjectEmployeesRepository projectEmployeesRepository;
	
	@PostMapping(value = "/assign")
    public String persist(@RequestBody final List<ProjectEmployees> projectEmployees) {
    	
		if(projectEmployees.size() <= 0) {
			return String.format("Not enough information provided");
		}
		
		Integer projId = projectEmployees.get(0).getProjectId();
		if(ProjectResource.checkIfProjectExists(projId) == false) {
			return String.format("Failed to find project");
		}
    	
		for(ProjectEmployees projEmp : projectEmployees) {
			ProjectEmployeesResource.assignEmpToProj(projEmp.getEmployeeId(), projId);
		}
    	
    	return String.format("Employees have been added to project");
    }
	
	@PostMapping(value = "/remove")
    public String persistRemove(@RequestBody final List<ProjectEmployees> projectEmployees) {
    	
		if(projectEmployees.size() <= 0) {
			return String.format("Not enough information provided");
		}
		
		Integer projId = projectEmployees.get(0).getProjectId();
		if(ProjectResource.checkIfProjectExists(projId) == false) {
			return String.format("Failed to find project");
		}
    	
		for(ProjectEmployees projEmp : projectEmployees) {
			ProjectEmployeesResource.deleteEmpFromProj(projEmp.getEmployeeId(), projId);
		}
    	
    	return String.format("Employees have been deleted from project");
    }
	
	public static boolean checkRepo() {
		if(projectEmployeesRepository == null) {
			return false;
		}
		return true;
	}
	
	public static void setRepo(ProjectEmployeesRepository inProjectEmployeesRepository) {
		projectEmployeesRepository = inProjectEmployeesRepository;
	}
	
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
	
	public static boolean deleteAllEmpFromProj(Integer projId) {
		List<ProjectEmployees> projEmps = getEmployeesInProject(projId);
		if(projEmps == null) {
			return true;
		}
		for(ProjectEmployees projEmp : projEmps) {
			if(deleteEmpFromProj(projEmp.getEmployeeId(), projId) == false) {
				return false;
			}
		}
		return true;
	}
	
	public static List<ProjectEmployees> getEmployeesInProject(Integer projId) {
		if(checkRepo() == false) {
			return null;
		}
		return projectEmployeesRepository.findByProjectId(projId);
	}
	
	public static List<ProjectEmployees> getProjectsOfEmployee(Integer empId) {
		if(checkRepo() == false) {
			return null;
		}
		return projectEmployeesRepository.findByEmployeeId(empId);
	}
}
