package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.AdminDao;
import com.cmu.model.Admin;


@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao dao;

	public Admin findById(int id) {
		return dao.findById(id);
	}

	/*public Admin findBySSO(String sso) {
		Admin Admin = dao.findBySSO(sso);
		return Admin;
	}*/

	public void saveAdmin(Admin Admin) {
		dao.save(Admin);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateAdmin(Admin Admin) {
		Admin entity = dao.findById(Admin.getId());
		/*if(entity!=null){
			entity.setSsoId(Admin.getSsoId());
			entity.setFirstName(Admin.getFirstName());
			entity.setLastName(Admin.getLastName());
			entity.setEmail(Admin.getEmail());
			entity.setAdminDocuments(Admin.getAdminDocuments());
		}*/
	}

	
	/*public void deleteAdminBySSO(String sso) {
		dao.deleteBySSO(sso);
	}*/

	public List<Admin> findAllAdmins() {
		return dao.findAllAdmins();
	}

	/*public boolean isAdminSSOUnique(Integer id, String sso) {
		Admin Admin = findBySSO(sso);
		return ( Admin == null || ((id != null) && (Admin.getId() == id)));
	}*/
	
}
