package com.cmu.dao;

import java.util.List;

import com.cmu.model.Question;


public interface QuestionDao {

	Question findById(int id);
	
	//Question findBySSO(String sso);
	
	void save(Question Question);
	
	//void deleteBySSO(String sso);
	
	List<Question> findAllQuestions();

}

