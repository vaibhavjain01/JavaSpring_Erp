package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.ProjectVacancy;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectVacancyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/projectvacancy")
public class ProjectVacancyResource {
	private static ProjectVacancyRepository projectVacancyRepository;
	
	public static boolean checkRepo() {
		if(projectVacancyRepository == null) {
			return false;
		}
		return true;
	}
	
	public static void setRepo(ProjectVacancyRepository inProjectVacancyRepository) {
		projectVacancyRepository = inProjectVacancyRepository;
	}
	
	public static boolean addProjectVacancy(Integer projId, Integer jobStatusId,
			String estStartDate, String estEndDate) {
		if(checkRepo() == false) {
			return false;
		}
		ProjectVacancy projVacancy = new ProjectVacancy();
		projVacancy.setEstEndDate(estEndDate);
		projVacancy.setEstStartDate(estStartDate);
		projVacancy.setJobStatusId(jobStatusId);
		projVacancy.setProjectId(projId);
		return true;
	}
	
	public static boolean deleteProjectVacancy(Integer projId, Integer jobStatusId,
			String estStartDate, String estEndDate) {
		if(checkRepo() == false) {
			return false;
		}
		projectVacancyRepository.deleteByProjectIdAndJobStatusIdAndEstStartDateAndEstEndDate(
				projId, jobStatusId, estStartDate, estEndDate);
		return true;
	}
	
	public static List<ProjectVacancy> getVacancyByProjectId(Integer projId) {
		if(checkRepo() == false) {
			return null;
		}
		return projectVacancyRepository.findByProjectId(projId);
	}
	
	public static List<ProjectVacancy> getVacancyByJobStatusId(Integer jobStatusId) {
		if(checkRepo() == false) {
			return null;
		}
		return projectVacancyRepository.findByJobStatusId(jobStatusId);
	}
}
