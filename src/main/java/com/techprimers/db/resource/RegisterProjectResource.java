package com.techprimers.db.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.db.model.EmployeeDetails;
import com.techprimers.db.model.Project;
import com.techprimers.db.model.ProjectDetails;
import com.techprimers.db.model.ProjectEmployees;
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
    
    @PostMapping(value = "/assign")
    @Transactional
    public String assignEmp(@RequestBody final List<ProjectEmployees> projectEmployees) {
    	setRepos();
    	if(ProjectEmployeesResource.addProjectEmployees(projectEmployees) == false) {
    		return String.format("Failed to assign employees");
    	}
    	return String.format("Employees assigned successfully"); 
    }
    
    @PostMapping(value = "/remove")
    @Transactional
    public String removeEmp(@RequestBody final List<ProjectEmployees> projectEmployees) {
    	setRepos();
    	if(ProjectEmployeesResource.removeProjectEmployee(projectEmployees) == false) {
    		return String.format("Failed to remove employees");
    	}
    	return String.format("Employees removed successfully"); 
    }
    
    @PostMapping(value = "/modify")
    public String modify(@RequestBody final ProjectDetails projectDetail) {
    	setRepos();
    	
    	if(ProjectResource.modifyProject(projectDetail.getProjectName(), 
    			projectDetail.getStartDate(), projectDetail.getEndDate()) == false) {
    		return String.format("Failed to modify project");
    	}
    	
    	Integer projectId = projectRepository.findByProjectName(projectDetail.getProjectName()).getProjectId();
    	
    	if(ProjectBudgetResource.modifyProjectBudget(projectId, projectDetail.getProjectBudget()) == false) {
    		return String.format("Failed to modify project");
    	}
    	
    	return String.format("Project has been modified");
    }
    
    @PostMapping(value = "/search")
    public String search(@RequestBody final Project project) {
    	setRepos();
    	Project foundProject = null;
    	List<Project> projects = null;
    	
    	if(project.getProjectName() != null) {
    		foundProject = projectRepository.findByProjectName(project.getProjectName());
    	}
    	else if(project.getStartDate() != null) {
    		projects = projectRepository.findByStartDate(project.getStartDate());
    	}
    	else if(project.getEndDate() != null) {
    		projects = projectRepository.findByStartDate(project.getEndDate());
    	}
    	
    	String rt = "No Project Found";
    	if(foundProject != null) {
    		rt = "Id: " + foundProject.getProjectId() + ", Name: " + foundProject.getProjectName() +
    				", StartDate: " + foundProject.getStartDate() + ", EndDate: " + foundProject.getEndDate();
    	}
    	else if(projects != null) {
    		for(int i = 0; i < projects.size(); i++) {
    			foundProject = projects.get(i);
    			if(i == 0) {
    				rt = "Id: " + foundProject.getProjectId() + ", Name: " + foundProject.getProjectName() +
        				", StartDate: " + foundProject.getStartDate() + ", EndDate: " + foundProject.getEndDate();
    			}
    			else {
    				rt += "\n\nId: " + foundProject.getProjectId() + ", Name: " + foundProject.getProjectName() +
            				", StartDate: " + foundProject.getStartDate() + ", EndDate: " + foundProject.getEndDate();
    			}
    		}
    	}
    	
    	return rt;
    	
    }
    
    public void setRepos() {
    	ProjectResource.setRepo(projectRepository);
    	ProjectBudgetResource.setRepo(projectBudgetRepository);
    	ProjectEmployeesResource.setRepo(projectEmployeesRepository);
    	ProjectVacancyResource.setRepo(projectVacancyRepository);
    }
}
