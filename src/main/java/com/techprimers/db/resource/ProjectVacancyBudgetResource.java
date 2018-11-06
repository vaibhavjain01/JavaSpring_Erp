package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectVacancyBudget;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectVacancyBudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectvacancybudget")
public class ProjectVacancyBudgetResource {
	private static ProjectVacancyBudgetRepository projectVacancyBudgetRepository;
	
	public static boolean checkRepo() {
		if(projectVacancyBudgetRepository == null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkIfBudgetExists(Integer projVacancyId) {
		if(checkRepo() == false) {
			return false;
		}
		if(projectVacancyBudgetRepository.findByProjectVacancyId(projVacancyId) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addProjectVacancyBudget(Integer projVacancyId, Integer budgetAmt) {
		if(checkRepo() == false) {
			return false;
		}
		ProjectVacancyBudget obj = new ProjectVacancyBudget();
		obj.setProjectVacancyBudgetAmt(budgetAmt);
		obj.setProjectVacancyId(projVacancyId);
		projectVacancyBudgetRepository.save(obj);
		return true;
	}
	
	public static boolean modifyProjectVacancyBudget(Integer projVacancyId, Integer budgetAmt) {
		if(checkRepo() == false) {
			return false;
		}
		Integer budgetId = projectVacancyBudgetRepository.findByProjectVacancyId(projVacancyId).getProjectVacancyBudgetId();
		ProjectVacancyBudget obj = new ProjectVacancyBudget();
		obj.setProjectVacancyBudgetId(budgetId);
		obj.setProjectVacancyBudgetAmt(budgetAmt);
		obj.setProjectVacancyId(projVacancyId);
		projectVacancyBudgetRepository.save(obj);
		return true;
	}
	
	public static boolean deleteProjectVacancyBudget(Integer projVacancyId) {
		if(checkRepo() == false) {
			return false;
		}
		projectVacancyBudgetRepository.deleteByProjectVacancyId(projVacancyId);
		return true;
	}
}
