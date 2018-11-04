package com.techprimers.db.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EmployeeDetails> persist(@RequestBody final EmployeeDetails employeeDetail) {
    	Users user = new Users();
    	user.setUsername(employeeDetail.getUsername());
    	user.setPassword(employeeDetail.getPassword());
    	userRepository.save(user);
    	
    	Address address = new Address();
    	address.setAddress(employeeDetail.getAddress());
    	addressRepository.save(address);
    	
    	JobStatus jobStatus = new JobStatus();
    	jobStatus.setJobStatusText(employeeDetail.getJobStatusText());
    	jobStatusRepository.save(jobStatus);
    	
    	Employee employee = new Employee();
    	int addressId = addressRepository.findByAddress(employeeDetail.getAddress()).getAddressId();
    	employee.setAddressId(addressId);
    	employee.setDob(employeeDetail.getDob());
    	employee.setEmail(employeeDetail.getEmail());
    	int jobStatusId = jobStatusRepository.findByJobStatusText(employeeDetail.getJobStatusText()).getJobStatusId();
    	employee.setJobStatusId(jobStatusId);
    	employee.setName(employeeDetail.getName());
    	employee.setUsername(employeeDetail.getUsername());
    	employee.setSalaryPerHour(employeeDetail.getSalaryPerHour());
    	employeeRepository.save(employee);
    	
    	int employeeId = employeeRepository.findByUsername(employeeDetail.getUsername()).getEmployeeId();
    	EmployeeResume employeeResume = new EmployeeResume();
    	employeeResume.setEmployeeId(employeeId);
    	employeeResume.setResumeDate(employeeDetail.getResumeDate());
    	employeeResume.setResumeText(employeeDetail.getResumeText());
    	employeeResumeRepository.save(employeeResume);
    	
    	List<Skills> skillNames = employeeDetail.getSkillName();
    	List<EmployeeSkills> skillExperience = employeeDetail.getYearsExperience();
    	
    	for(int i = 0; i < skillNames.size(); i++) {
    		Skills skillName = skillNames.get(i);
    		skillsRepository.save(skillName);
    		
    		int skillId = skillsRepository.findBySkillName(skillName.getSkillName()).getSkillId();
    		EmployeeSkills exp = skillExperience.get(i);
    		exp.setEmployeeId(employeeId);
    		exp.setSkillId(skillId);
    		employeeSkillsRepository.save(exp);
    		//employeeSkills.setEmployeeId();
    		//employeeSkills.setSkillId();
    		//employeeSkills.setYearsExperience(skillExperience.get(i));
    	}
    	
    	EmployeePerformance employeePerformance = new EmployeePerformance();
    	employeePerformance.setEmployeeId(employeeId);
    	employeePerformance.setRatingScaleTen(0);
    	employeePerformance.setRatingYear(0);
    	employeePerformanceRepository.save(employeePerformance);
    	
        return null;
    }
}
