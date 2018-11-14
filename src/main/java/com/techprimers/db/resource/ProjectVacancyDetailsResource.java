package com.techprimers.db.resource;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.db.model.ProjectDetails;
import com.techprimers.db.model.ProjectVacancy;
import com.techprimers.db.model.ProjectVacancyDetails;
import com.techprimers.db.repository.ProjectBudgetRepository;
import com.techprimers.db.repository.ProjectEmployeesRepository;
import com.techprimers.db.repository.ProjectRepository;
import com.techprimers.db.repository.ProjectVacancyBudgetRepository;
import com.techprimers.db.repository.ProjectVacancyRepository;
import com.techprimers.db.repository.ProjectVacancySkillsRepository;

@RestController
@RequestMapping(value = "/rest/projvacancy")
public class ProjectVacancyDetailsResource {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ProjectBudgetRepository projectBudgetRepository;
	@Autowired
	ProjectVacancyRepository projectVacancyRepository;
	@Autowired
	ProjectEmployeesRepository projectEmployeesRepository;
	@Autowired
	ProjectVacancyBudgetRepository projectVacancyBudgetRepository;
	@Autowired
	ProjectVacancySkillsRepository projectVacancySkillsRepository;
	
    @PostMapping(value = "/load")
    public String persist(@RequestBody final ProjectVacancyDetails projVacDetail) {
    	setRepos();
    	
    	if(ProjectVacancyResource.addProjectVacancy(projVacDetail.getProject_id()
    			, projVacDetail.getJob_status_id(), projVacDetail.getEst_start_date()
    			, projVacDetail.getEst_end_date()) == false) {
    		return String.format("Failed to add vacancy");
    	}
    	
    	List<ProjectVacancy> projVacancy = 
    			projectVacancyRepository.findByProjectIdAndJobStatusIdAndEstStartDateAndEstEndDate(
    					projVacDetail.getProject_id(), projVacDetail.getJob_status_id(), 
    					projVacDetail.getEst_start_date(), projVacDetail.getEst_end_date());
    	
    	Integer projVacancyId = projVacancy.get(0).getProjectVacancyId();
    	
    	if(ProjectVacancyBudgetResource.addProjectVacancyBudget(projVacancyId, 
    			projVacDetail.getProject_vacancy_amt()) == false) {
    		return String.format("Failed to add vacancy");
    	}
    	
    	if(ProjectVacancySkillsResource.addProjectVacancySkill(projVacancyId, 
    			projVacDetail.getSkill_id(), projVacDetail.getYears_experience()) == false) {
    		return String.format("Failed to add vacancy");
    	}
    	
    	return String.format("Project Vacancy has been added");
    }
    
    
    @PostMapping(value = "/remove")
    @Transactional
    public String remove(@RequestBody final ProjectVacancy projVac) {
    	setRepos();
    	
    	List<Integer> projVacIds = new ArrayList<Integer>();
    	
    	if(projVac.getProjectVacancyId() == null) {
    		List<ProjectVacancy> projVacs =  ProjectVacancyResource.getVacancyByProjectId(projVac.getProjectId());
        	for(int i = 0; i < projVacs.size(); i++) {
        		projVacIds.add(projVacs.get(i).getProjectVacancyId());
        	}
        	if(ProjectVacancyResource.deleteAllProjectVacancies(projVac.getProjectId()) == false) {
        		return String.format("Failed to remove vacancies");
        	}
    	} else {
    		projVacIds.add(projVac.getProjectVacancyId());
    		if(ProjectVacancyResource.deleteProjectVacancy(projVac.getProjectVacancyId()) == false) {
        		return String.format("Failed to remove vacancies");
        	}
    	}

    	for(Integer projVacId : projVacIds) {
    		if(ProjectVacancyBudgetResource.deleteProjectVacancyBudget(projVacId) == false) {
        		return String.format("Failed to remove vacancies");
        	}
        	
        	if(ProjectVacancySkillsResource.deleteProjectVacancySkill(projVacId) == false) {
        		return String.format("Failed to remove vacancies");
        	}
    	}
    	
    	return String.format("Project Vacancy has been deleted");
    }
    
    
    public void setRepos() {
    	ProjectResource.setRepo(projectRepository);
    	ProjectBudgetResource.setRepo(projectBudgetRepository);
    	ProjectEmployeesResource.setRepo(projectEmployeesRepository);
    	ProjectVacancyResource.setRepo(projectVacancyRepository);
    	ProjectVacancyBudgetResource.setRepo(projectVacancyBudgetRepository);
    	ProjectVacancySkillsResource.setRepo(projectVacancySkillsRepository);
    }
	
}