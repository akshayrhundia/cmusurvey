package com.cmu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmu.dao.UserDao;
import com.cmu.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByuser(String usr) {
		User user = dao.findByuser(usr);
		return user;
	}

	public void saveUser(User user) {
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setAge(user.getAge());
			entity.setGender(user.getGender());
			entity.setOccupation(user.getOccupation());
			entity.setUserId(user.getUserId());
			
		}
	}

	
	public void deleteUserByuser(String user) {
		dao.deleteByuser(user);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUseruserUnique(Integer id, String usr) {
		User user = findByuser(usr);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
