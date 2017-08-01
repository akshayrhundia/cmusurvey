package com.cmu.service;

import java.util.List;

import com.cmu.model.UserAnswers;


public interface UserAnswersService {
	
	
	UserAnswers findUserAnswerByQuestionId(int id,String userId);
	
	//UserAnswers findBySSO(String sso);
	
	void saveUserAnswer(UserAnswers UserAnswer);
	
	void updateUserAnswer(UserAnswers UserAnswer);
	
	//void deleteUserAnswersBySSO(String sso);

	List<UserAnswers> findAllUserAnswers(String userId); 
	
	void deleteUserAnswerById(Integer qId,String userId);
	
	//boolean isUserAnswersSSOUnique(Integer id, String sso);

}