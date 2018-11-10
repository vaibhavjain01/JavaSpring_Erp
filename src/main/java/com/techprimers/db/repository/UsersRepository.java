package com.techprimers.db.repository;

import com.techprimers.db.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);
	Integer deleteByUsername(String username);
	Users findByUsernameAndPassword(String username, String password);
}
