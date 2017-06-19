package com.cmu.dao;

import com.cmu.model.User;

public interface UserDao {

	User findByUserName(String username);

}