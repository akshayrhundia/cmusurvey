package com.cmu.service;

import java.util.List;

import com.cmu.model.Question;


public interface QuestionService {
	
	Question findById(int id);
	
	//Question findBySSO(String sso);
	
	void saveQuestion(Question Question);
	
	void updateQuestion(Question Question);
	
	//void deleteQuestionBySSO(String sso);

	List<Question> findAllQuestions(); 
	
	//boolean isQuestionSSOUnique(Integer id, String sso);

}