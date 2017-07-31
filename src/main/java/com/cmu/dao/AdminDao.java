package com.cmu.dao;

import java.util.List;

import com.cmu.model.Admin;


public interface AdminDao {

	Admin findById(int id);
	
	//Admin findBySSO(String sso);
	
	void save(Admin Admin);
	
	//void deleteBySSO(String sso);
	
	List<Admin> findAllAdmins();

}

