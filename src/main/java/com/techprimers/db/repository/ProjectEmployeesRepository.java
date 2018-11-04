package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.ProjectEmployees;

public interface ProjectEmployeesRepository extends JpaRepository<ProjectEmployees, Integer> {

}
