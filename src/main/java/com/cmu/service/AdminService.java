package com.cmu.service;

import java.util.List;

import com.cmu.model.Admin;


public interface AdminService {
	
	Admin findById(int id);
	
	//Admin findBySSO(String sso);
	
	void saveAdmin(Admin Admin);
	
	void updateAdmin(Admin Admin);
	
	//void deleteAdminBySSO(String sso);

	List<Admin> findAllAdmins(); 
	
	//boolean isAdminSSOUnique(Integer id, String sso);

}