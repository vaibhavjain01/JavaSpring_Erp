package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.Skills;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.SkillsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/skills")
public class SkillsResource {

	@Autowired
	private static SkillsRepository skillsRepository;
	
	public static void setRepo(SkillsRepository inSkillsRepository) {
		skillsRepository = inSkillsRepository;
    }
	
	public static Skills checkOrAddSkill(Skills skill) {
		Skills res = skillsRepository.findBySkillName(skill.getSkillName());
		if(res == null) {
			skillsRepository.save(skill);
			res = skillsRepository.findBySkillName(skill.getSkillName());
		}
		return res;
	}
	
	public static Integer getSkillId(Skills skill) {
		int skillId = checkOrAddSkill(skill).getSkillId();
		return skillId;
	}
}
