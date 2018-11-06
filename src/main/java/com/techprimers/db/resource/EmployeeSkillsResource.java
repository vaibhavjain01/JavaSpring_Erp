package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.EmployeePerformance;
import com.techprimers.db.model.EmployeeSkills;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.EmployeeSkillsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/employeeskills")
public class EmployeeSkillsResource {
	@Autowired
	private static EmployeeSkillsRepository employeeSkillsRepository;
	
	public static void setRepo(EmployeeSkillsRepository inEmployeeSkillsRepository) {
		employeeSkillsRepository = inEmployeeSkillsRepository;
    }
	
	public static boolean checkIfEmployeeSkillsExist(Integer empId) {
		EmployeeSkills res = employeeSkillsRepository.findByEmployeeId(empId);
		if(res == null) {
			return false;
		}
		return true;
	}
	
	public static boolean deleteAllSkills(Integer empId) {
		EmployeeSkills res = employeeSkillsRepository.findByEmployeeId(empId);
		if(res != null) {
			employeeSkillsRepository.deleteByEmployeeId(empId);
		}
		return true;
	}
	
	public static boolean addEmployeeSkills(Integer empId, Integer skillId, Integer yearsOfExp) {
		EmployeeSkills empSkill = new EmployeeSkills();
		empSkill.setEmployeeId(empId);
		empSkill.setSkillId(skillId);
		empSkill.setYearsExperience(yearsOfExp);
		employeeSkillsRepository.save(empSkill);
		return true;
	}
	
	public static boolean addNewEmployeeSkills(Integer empId, Integer skillId, Integer yearsOfExp) {
		return addEmployeeSkills(empId, skillId, yearsOfExp);		
	}
	
	public static boolean modifyEmployeeSkills(Integer skillId, Integer empId, Integer yearsOfExp) {
		if(employeeSkillsRepository == null) {
			return false;
		}
		return addEmployeeSkills(empId, skillId, yearsOfExp);
	}
}
