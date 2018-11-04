package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Integer> {
	Skills findBySkillName(String skillName);
}
