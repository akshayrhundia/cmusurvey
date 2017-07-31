package com.cmu.service;

import java.util.List;

import com.cmu.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByuser(String user);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByuser(String user);

	List<User> findAllUsers(); 
	
	boolean isUseruserUnique(Integer id, String user);

}