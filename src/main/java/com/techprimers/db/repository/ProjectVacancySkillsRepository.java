package com.techprimers.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectVacancySkills;

public interface ProjectVacancySkillsRepository extends JpaRepository<ProjectVacancySkills, Integer> {
	List<ProjectVacancySkills> findByProjectVacancyId(Integer projectVacancyId);
	List<ProjectVacancySkills> findBySkillId(Integer skillId);
	Integer deleteByProjectVacancyIdAndSkillId(Integer projectVacancyId, Integer skillId);
}
