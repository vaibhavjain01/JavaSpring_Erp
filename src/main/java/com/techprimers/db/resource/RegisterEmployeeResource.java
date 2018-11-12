package com.techprimers.db.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Employee;
import com.techprimers.db.model.EmployeeDetails;
import com.techprimers.db.model.EmployeePerformance;
import com.techprimers.db.model.EmployeeResume;
import com.techprimers.db.model.EmployeeSkills;
import com.techprimers.db.model.JobStatus;
import com.techprimers.db.model.Skills;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.EmployeeRepository;
import com.techprimers.db.repository.EmployeeResumeRepository;
import com.techprimers.db.repository.EmployeeSkillsRepository;
import com.techprimers.db.repository.JobStatusRepository;
import com.techprimers.db.repository.SkillsRepository;
import com.techprimers.db.repository.UsersRepository;

@RestController
@RequestMapping(value = "/rest/registeremployee")
public class RegisterEmployeeResource {
	
	@Autowired
	UsersRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	JobStatusRepository jobStatusRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeResumeRepository employeeResumeRepository;
	@Autowired
	EmployeeSkillsRepository employeeSkillsRepository;
	@Autowired
	SkillsRepository skillsRepository;
	@Autowired
	EmployeePerformanceRepository employeePerformanceRepository;

    @PostMapping(value = "/load")
    public String persist(@RequestBody final EmployeeDetails employeeDetail) {
    	setAllRepo();
    	
    	if((UsersResource.checkIfUserExist(employeeDetail.getUsername()) == true) ||
    			(EmployeeResource.checkIfEmployeeExist(employeeDetail.getUsername()) == true)){
    		return String.format("Username already exists");
    	}
    	if(UsersResource.addNewUser(employeeDetail.getUsername(), 
    			employeeDetail.getPassword()) == false) {
    		return String.format("Failed to add user");
    	}
    	
    	int addressId = AddressResource.getAddressId(employeeDetail.getAddress());
    	int jobStatusId = JobStatusResource.getJobStatusId(employeeDetail.getJobStatusText());
    	
    	if(EmployeeResource.addNewEmployee(employeeDetail.getName(), jobStatusId,
				employeeDetail.getDob(), employeeDetail.getEmail(), employeeDetail.getUsername(),
				addressId, employeeDetail.getSalaryPerHour()) == false) {
    		return String.format("Failed to add employee");
		}
    	
    	int employeeId = EmployeeResource.getEmployeeId(employeeDetail.getUsername());
    	if(EmployeeResumeResource.addNewEmployeeResume(employeeId, employeeDetail.getResumeDate(), 
    			employeeDetail.getResumeText()) == false) {
    		return String.format("Failed to add employee resume");
    	}
    	
    	List<Skills> skillNames = employeeDetail.getSkillName();
    	List<EmployeeSkills> skillExperience = employeeDetail.getYearsExperience();
    	EmployeeSkillsResource.deleteAllSkills(employeeId);
    	
    	for(int i = 0; i < skillNames.size(); i++) {
    		Integer skillId = SkillsResource.getSkillId(skillNames.get(i));
    		if(EmployeeSkillsResource.addNewEmployeeSkills(employeeId, skillId, 
    				skillExperience.get(i).getYearsExperience()) == false) {
    			return String.format("Failed to add employee skill");
    		}
    	}
    	
    	if(EmployeePerformanceResource.addNewEmployeePerformance(employeeId) == false) {
    		return String.format("Failed to add employee performance");
    	}
    	
    	return String.format("Employee has been added");
    }
    
    @PostMapping(value = "/delete")
    @Transactional
    public String deleteEmployee(@RequestBody final EmployeeDetails employeeDetail) {
    	setAllRepo();
    	
    	if((UsersResource.checkIfUserExist(employeeDetail.getUsername()) != true) ||
    			(EmployeeResource.checkIfEmployeeExist(employeeDetail.getUsername()) != true)){
    		return String.format("Username doesn't exists");
    	}
    	
    	Employee emp = employeeRepository.findByUsername(employeeDetail.getUsername());
    	Integer employeeId = emp.getEmployeeId();
    	Integer addressId = emp.getAddressId();
    	Integer jobStatusId = emp.getJobStatusId();
    	
    	if(EmployeePerformanceResource.deleteEmployeePerformance(employeeId) == false) {
    		return String.format("Failed to delete employee performance");
    	}

    	if(EmployeeSkillsResource.deleteAllSkills(employeeId) == false) {
    		return String.format("Failed to delete employee skills");
    	}
    	
    	if(EmployeeResumeResource.deleteEmployeeResume(employeeId) == false) {
    		return String.format("Failed to delete employee resume");
    	}
    	
    	if(AddressResource.deleteAddress(addressId) == false) {
    		return String.format("Failed to delete address");
    	}
    	
    	if(JobStatusResource.deleteJobStatus(jobStatusId) == false) {
    		return String.format("Failed to delete address");
    	}
    	
    	if(UsersResource.deleteUser(employeeDetail.getUsername()) == false) {
    		return String.format("Failed to delete username");
    	}
    	
    	if(EmployeeResource.deleteEmployee(employeeDetail.getUsername()) == false) {
    		return String.format("Failed to delete employee");
    	}
    		
    	return String.format("Employee has been deleted");
    }
    
    @PostMapping(value = "/searchEmpSkill")
    @Transactional
    public String searchEmployeeBySkill(@RequestBody final Skills skill) {
    	setAllRepo();
    	Skills tempSkill = skillsRepository.findBySkillName(skill.getSkillName());
    	if(tempSkill == null) {
    		return String.format("No such skill exists in available skill pool");
    	}
    	Integer skillId = tempSkill.getSkillId();
    	List<EmployeeSkills> empWithSkill = employeeSkillsRepository.findBySkillId(skillId);
    	String employeesWithSkill = "{";
    	for (int ctr = 0; ctr < empWithSkill.size(); ctr++) {
    		Integer empId = empWithSkill.get(ctr).getEmployeeId();
    		employeesWithSkill += "\"employeeName\" : \"" + employeeRepository.findByEmployeeId(empId).getName() + "\"";
    		if((ctr + 1) < empWithSkill.size()) {
    			employeesWithSkill += ",";
    		}
    		else {
    			employeesWithSkill += "}";
    		}
    	}
    	if(employeesWithSkill.length() > 2) {
    		return String.format(employeesWithSkill);
    	}
    	return String.format("No employees found with that skill.");
    }
    
    @PostMapping(value = "/setPerf")
    @Transactional
    public String setPerformanceInfo(@RequestBody final EmployeePerformance empPerf) {
    	setAllRepo();
		Integer empId = empPerf.getEmployeeId();
		Integer ratingYear = empPerf.getRatingYear();
		Integer ratingScale = empPerf.getRatingScaleTen();

		EmployeePerformanceResource.addEmployeePerformance(empId, ratingYear, ratingScale);
		
		return String.format("Employee Performance Updated");
    }
    
    public void setAllRepo() {
    	AddressResource.setRepo(addressRepository);
    	EmployeePerformanceResource.setRepo(employeePerformanceRepository);
    	EmployeeResource.setRepo(employeeRepository);
    	EmployeeResumeResource.setRepo(employeeResumeRepository);
    	EmployeeSkillsResource.setRepo(employeeSkillsRepository);
    	JobStatusResource.setRepo(jobStatusRepository);
    	SkillsResource.setRepo(skillsRepository);
    	UsersResource.setRepo(userRepository);
    }
}
