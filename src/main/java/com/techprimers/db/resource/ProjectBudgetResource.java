package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectBudget;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectBudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectbudget")
public class ProjectBudgetResource {
	@Autowired
	private static ProjectBudgetRepository projectBudgetRepository;
	
	public static void setRepo(ProjectBudgetRepository inProjectBudgetRepository) {
		projectBudgetRepository = inProjectBudgetRepository;
	}
	
	public static boolean checkRepo() {
		if(projectBudgetRepository == null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkIfProjectBudgetExists(Integer projId) {
		if(checkRepo() == false) {
			return false;
		}
		if(projectBudgetRepository.findByProjectId(projId) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addProjectBudget(Integer projId, Integer projectBudgetAmt) {
		if(checkRepo() == false) {
			return false;
		}
		ProjectBudget projBudget = new ProjectBudget();
		projBudget.setProjectBudgetAmt(projectBudgetAmt);
		projBudget.setProjectId(projId);
		projectBudgetRepository.save(projBudget);
		return true;
	}
	
	public static boolean modifyProjectBudget(Integer projId, Integer projectBudgetAmt) {
		if(checkRepo() == false) {
			return false;
		}
		Integer projBudgetId = projectBudgetRepository.findByProjectId(projId).getProjectBudgetId();
		ProjectBudget projBudget = new ProjectBudget();
		projBudget.setProjectBudgetId(projBudgetId);
		projBudget.setProjectBudgetAmt(projectBudgetAmt);
		projBudget.setProjectId(projId);
		projectBudgetRepository.save(projBudget);
		return true;
	}
	
	public static boolean deleteProjectBudget(Integer projId) {
		if(checkRepo() == false) {
			return false;
		}
		Integer projBudgetId = projectBudgetRepository.findByProjectId(projId).getProjectBudgetId();
		projectBudgetRepository.delete(projBudgetId);
		return true;
	}
}
