package com.cmu.dao;

import java.util.List;

import com.cmu.model.UserAnswers;


public interface UserAnswersDao {

	UserAnswers findUserAnswerByQuestionId(int id,String userId);
	
	//UserAnswers findBySSO(String sso);
	
	void save(UserAnswers UserAnswers);
	
	void deleteUserAnswerById(Integer qId,String userId);
	
	List<UserAnswers> findAllUserAnswers(String userId);

}

