package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectVacancy;

public interface ProjectVacancyRepository extends JpaRepository<ProjectVacancy, Integer> {

}
