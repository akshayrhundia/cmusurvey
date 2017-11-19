package com.cmu.dao;

import java.util.List;

import com.cmu.model.QuestionText;


public interface QuestionTextDao {

	QuestionText findById(int id);
	
	//Question findBySSO(String sso);
	
	void save(QuestionText Question);
	
	//void deleteBySSO(String sso);
	
	List<QuestionText> findAllQuestions();

}

