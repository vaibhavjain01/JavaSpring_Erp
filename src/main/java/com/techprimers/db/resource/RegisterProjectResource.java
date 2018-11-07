package com.techprimers.db.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.db.model.EmployeeDetails;
import com.techprimers.db.model.Project;
import com.techprimers.db.model.ProjectDetails;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.EmployeeRepository;
import com.techprimers.db.repository.EmployeeResumeRepository;
import com.techprimers.db.repository.EmployeeSkillsRepository;
import com.techprimers.db.repository.JobStatusRepository;
import com.techprimers.db.repository.ProjectBudgetRepository;
import com.techprimers.db.repository.ProjectEmployeesRepository;
import com.techprimers.db.repository.ProjectRepository;
import com.techprimers.db.repository.ProjectVacancyRepository;
import com.techprimers.db.repository.SkillsRepository;
import com.techprimers.db.repository.UsersRepository;

@RestController
@RequestMapping(value = "/rest/registerproject")
public class RegisterProjectResource {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ProjectBudgetRepository projectBudgetRepository;
	@Autowired
	ProjectVacancyRepository projectVacancyRepository;
	@Autowired
	ProjectEmployeesRepository projectEmployeesRepository;
	
    @PostMapping(value = "/load")
    public String persist(@RequestBody final ProjectDetails projectDetail) {
    	setRepos();
    	
    	if(ProjectResource.addProject(projectDetail.getProjectName(), 
    			projectDetail.getStartDate(), projectDetail.getEndDate()) == false) {
    		return String.format("Failed to add project");
    	}
    	
    	Integer projectId = projectRepository.findByProjectName(projectDetail.getProjectName()).getProjectId();
    	
    	if(ProjectBudgetResource.addProjectBudget(projectId, projectDetail.getProjectBudget()) == false) {
    		return String.format("Failed to add project");
    	}
    	
    	return String.format("Project has been added");
    }
    
    @PostMapping(value = "/delete")
    @Transactional
    public String delete(@RequestBody final ProjectDetails projectDetail) {
    	setRepos();
    	
    	Project projectInfo = projectRepository.findByProjectName(projectDetail.getProjectName());
    	
    	if(ProjectBudgetResource.deleteProjectBudget(projectInfo.getProjectId()) == false) {
    		return String.format("Failed to delete project budget");
    	}
    	
    	if(ProjectResource.deleteProject(projectInfo.getProjectName()) == false) {
    		return String.format("Failed to delete project");
    	}
    	
    	if(ProjectEmployeesResource.deleteAllEmpFromProj(projectInfo.getProjectId()) == false) {
    		return String.format("Failed to delete project employees");
    	}
    	
    	if(ProjectVacancyResource.deleteAllProjectVacancies(projectInfo.getProjectId()) == false) {
    		return String.format("Failed to delete project vacancies");
    	}
    	
    	return String.format("Project has been deleted");
    }
    
    public void setRepos() {
    	ProjectResource.setRepo(projectRepository);
    	ProjectBudgetResource.setRepo(projectBudgetRepository);
    	ProjectEmployeesResource.setRepo(projectEmployeesRepository);
    	ProjectVacancyResource.setRepo(projectVacancyRepository);
    }
}
