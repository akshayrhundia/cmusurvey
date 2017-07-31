package com.cmu.dao;

import java.util.List;

import com.cmu.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByuser(String user);
	
	void save(User user);
	
	void deleteByuser(String user);
	
	List<User> findAllUsers();

}

