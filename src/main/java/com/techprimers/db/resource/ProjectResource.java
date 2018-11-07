package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Project;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/project")
public class ProjectResource {
	private static ProjectRepository projectRepository;
	
	public static boolean checkRepo() {
		if(projectRepository == null) {
			return false;
		}
		return true;
	}
	
	public static void setRepo(ProjectRepository inProjectRepository) {
		projectRepository = inProjectRepository;
	}
	
	public static boolean addProject(String projectName, String startDate, String endDate) {
		if(projectRepository == null) {
			return false;
		}
		Project proj = new Project();
		proj.setStartDate(startDate);
		proj.setEndDate(endDate);
		proj.setProjectName(projectName);
		projectRepository.save(proj);
		return true;
	}
	
	public static boolean modifyProject(String projectName, String startDate, String endDate) {
		if(projectRepository == null) {
			return false;
		}
		Integer projId = projectRepository.findByProjectName(projectName).getProjectId();
		if(projId == null) {
			return false;
		}
		Project proj = new Project();
		proj.setProjectId(projId);
		proj.setStartDate(startDate);
		proj.setEndDate(endDate);
		proj.setProjectName(projectName);
		projectRepository.save(proj);
		return true;
	}
	
	public static boolean checkIfProjectExists(String projectName) {
		if(projectRepository == null) {
			return false;
		}
		if(projectRepository.findByProjectName(projectName) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkIfProjectExists(Integer projectId) {
		if(projectRepository == null) {
			return false;
		}
		if(projectRepository.findByProjectId(projectId) == null) {
			return false;
		}
		return true;
	}
	
	public static boolean deleteProject(String projectName) {
		if(checkIfProjectExists(projectName) == true) {
			projectRepository.deleteByProjectName(projectName);
		}
		else {
			return false;
		}
		return true;
	}
}
