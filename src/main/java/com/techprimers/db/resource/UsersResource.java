package com.techprimers.db.resource;

import com.techprimers.db.model.Skills;
import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    private static UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public static void setRepo(UsersRepository inUsersRepository) {
    	usersRepository = inUsersRepository;
    }
    
    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final List<Users> users) {
    	if(usersRepository == null) {
    		
    	}
    	for(Users user : users) {
    		usersRepository.save(user);
    	}
        return usersRepository.findAll();
    }
    
    public static boolean checkIfUserExist(String username) {
    	Users res = usersRepository.findByUsername(username);
		if(res == null) {
			return false;
		}
    	return true;
    }
    
    public static boolean addUser(String username, String password) {
    	Users res = usersRepository.findByUsername(username);
		if(res == null) {
			Users user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			usersRepository.save(user);
			return true;
		}
		return false;
	}
	
	public static boolean addNewUser(String username, String password) {
		return addUser(username, password);
	}
	
	public static boolean deleteUser(String username) {
		if(usersRepository == null) {
			return false;
		}
		usersRepository.deleteByUsername(username);
		return true;
	}
	
	public static boolean changePassword(String username, String password) {
		if(deleteUser(username) == true) {
			return addNewUser(username, password);
		}
		return false;
	}

}
