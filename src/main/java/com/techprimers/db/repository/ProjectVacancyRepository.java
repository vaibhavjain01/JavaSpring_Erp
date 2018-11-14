package com.techprimers.db.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectVacancy;

public interface ProjectVacancyRepository extends JpaRepository<ProjectVacancy, Integer> {
	ProjectVacancy deleteByProjectIdAndJobStatusIdAndEstStartDateAndEstEndDate(
			Integer projectId, Integer jobStatusId, String estStartDate, String estEndDate);
	List<ProjectVacancy> findByProjectIdAndJobStatusIdAndEstStartDateAndEstEndDate(
			Integer projectId, Integer jobStatusId, String estStartDate, String estEndDate);
	List<ProjectVacancy> findByProjectId(Integer projectId);
	List<ProjectVacancy> findByJobStatusId(Integer jobStatusId);
	List<ProjectVacancy> findByProjectIdAndJobStatusId(Integer projectId, Integer jobStatusId);
}
