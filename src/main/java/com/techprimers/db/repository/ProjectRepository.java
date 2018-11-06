package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Project findByProjectName(String projectName);
	boolean deleteByProjectName(String projectName);
}
