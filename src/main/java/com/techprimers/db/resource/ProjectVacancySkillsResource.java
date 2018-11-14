package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectVacancySkills;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectVacancySkillsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectvacancyskills")
public class ProjectVacancySkillsResource {
	private static ProjectVacancySkillsRepository projectVacancySkillsRepository;
	
	public static boolean checkRepo() {
		if(projectVacancySkillsRepository == null) {
			return false;
		}
		return true;
	}
	
	public static void setRepo(ProjectVacancySkillsRepository inProjectVacancySkillsRepository) {
		projectVacancySkillsRepository = inProjectVacancySkillsRepository;
	}
	
	public static boolean checkProjectVacancySkill(Integer projVacId) {
		if(checkRepo() == false) {
			return false;
		}
		if(projectVacancySkillsRepository.findByProjectVacancyId(projVacId) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean addProjectVacancySkill(Integer projVacId, Integer skillId, Integer expYears) {
		if(checkRepo() == false) {
			return false;
		}
		ProjectVacancySkills projVacSkill = new ProjectVacancySkills();
		projVacSkill.setProjectVacancyId(projVacId);
		projVacSkill.setSkillId(skillId);
		projVacSkill.setYearsExperience(expYears);
		projectVacancySkillsRepository.save(projVacSkill);
		return true;
	}
	
	public static boolean modifyProjectVacancySkill(Integer projVacId, Integer skillId, Integer expYears) {
		if(checkRepo() == false) {
			return false;
		}
		return addProjectVacancySkill(projVacId, skillId, expYears);
	}
	
	public static boolean deleteProjectVacancySkill(Integer projVacId) {
		if(checkRepo() == false) {
			return false;
		}
		List<ProjectVacancySkills> projVacsSkills = 
				projectVacancySkillsRepository.findByProjectVacancyId(projVacId);
		for(ProjectVacancySkills pvs: projVacsSkills) {
			projectVacancySkillsRepository.deleteByProjectVacancyIdAndSkillId(projVacId, pvs.getSkillId());
		}
		return true;
	}
}
