package com.tokokakas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokokakas.entity.User;
import com.tokokakas.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	//register new user
	public void insertUser(User user) {
		userRepo.save(user);
	}
	
	//menampilkan user berdasarkan user_name
	public User getUserByUserName(String user_name) {
		return userRepo.findById(user_name).get();
	}
	
	//update data user
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	//check login
	public boolean checkUser(String user_name, String password) {
		String userCount = userRepo.checkUser(user_name, password);
		if(userCount.equals("0")) {
			return false;
		}else {
			return true;
		}
	}
}
