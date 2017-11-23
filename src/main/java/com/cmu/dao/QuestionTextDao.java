package com.cmu.dao;

import java.util.List;

import com.cmu.model.QuestionText;


public interface QuestionTextDao {

	QuestionText findById(int id);
	
	//Question findBySSO(String sso);
	
	void save(QuestionText Question);
	void update(QuestionText Question);
	void updateTitle(QuestionText Question);
	//void deleteBySSO(String sso);
	
	List<QuestionText> findAllQuestions();
	
	public void deleteById(int id);
	int getMax();

}

