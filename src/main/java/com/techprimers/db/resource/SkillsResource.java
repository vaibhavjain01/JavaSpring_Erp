package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.repository.AddressRepository;
import com.techprimers.db.repository.SkillsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/skills")
public class SkillsResource {

	@Autowired
	SkillsRepository skillRepository;
	
	static void checkIfSkillExist(String skillName) {
		
	}
}